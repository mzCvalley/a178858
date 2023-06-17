package com.fyp.a178858.service;

import com.fyp.a178858.entity.DailySalary;
import com.fyp.a178858.entity.Ot;
import com.fyp.a178858.entity.User;
import com.fyp.a178858.enums.DayTypeEnum;
import com.fyp.a178858.enums.OtRequestEnum;
import com.fyp.a178858.enums.UserTypeEnum;
import com.fyp.a178858.model.request.OtActionRequest;
import com.fyp.a178858.model.request.OtCreateRequest;
import com.fyp.a178858.repository.DailySalaryRepo;
import com.fyp.a178858.repository.OtRepo;
import com.fyp.a178858.repository.UserRepo;
import com.fyp.a178858.specification.OtSpecification;
import com.fyp.a178858.specification.SalarySpecification;
import com.fyp.a178858.util.ClockInUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Service
@Transactional
public class OtService {

    //Repository
    @Autowired
    OtRepo repository;
    @Autowired
    UserRepo userRepo;
    @Autowired
    DailySalaryRepo salaryRepo;

    public List<Ot> findByUser(Long userId) {
        Specification<Ot> spec = OtSpecification.buildUserHistory(userId);

        //Return a list of OT requests assigned to employee with ALL statuses
        return repository.findAll(spec);
    }

    public List<Ot> employerFindAll() {
        Specification<Ot> spec = OtSpecification.buildEmployer();

        //Return a list of OT request from employee that is on PENDING
        return repository.findAll(spec);
    }

    public List<Ot> employeeFindAll(Long userId) {
        Specification<Ot> spec = OtSpecification.buildEmployee(userId);

        //Return a list of OT request assigned to employee that is on PENDING
        return repository.findAll(spec);
    }

    public Boolean requestOt(OtCreateRequest request) {
        User userItem = userRepo.getReferenceById(request.getUserId());

        Ot newOt = Ot.builder.instance()
                .withOtDate(request.getOtDate())
                .withDuration(request.getDuration())
                .withJobDesc(request.getJobDesc())
                .withRequestStatus(OtRequestEnum.PENDING)
                .withUserType(UserTypeEnum.valueOf(request.getRequestUserType()))
                .withDayType(DayTypeEnum.valueOf(request.getDayType()))
                .withUser(userItem)
                .build();

        repository.save(newOt);

        return repository.findById(newOt.getId()).isPresent();
    }

    public Boolean actionOt(OtActionRequest request){
        Ot otItem = repository.getReferenceById(request.getId());

        otItem.setRequestStatus(OtRequestEnum.valueOf(request.getRequestAction()));
        repository.save(otItem);

        //Add to salary pay if confirmed
        if(OtRequestEnum.valueOf(request.getRequestAction()).equals(OtRequestEnum.COMPLETED)) {
            //Find record for today or create one if NOT FOUND
            Specification<DailySalary> salarySpec = SalarySpecification.build(otItem.getUser().getId(),
                    otItem.getOtDate());
            DailySalary todaySalary = salaryRepo.findOne(salarySpec)
                    .orElseGet(() -> salaryRepo.save(DailySalary.builder.instance()
                            .withUser(otItem.getUser())
                            .withRecordDate(LocalDate.now(ZoneId.of("Asia/Kuala_Lumpur")))
                            .withNormalPayAmount(BigDecimal.ZERO)
                            .withOtPayAmount(BigDecimal.ZERO)
                            .withTotalPayAmount(BigDecimal.ZERO)
                            .build()));

            //Get Daily Salary & Add to Sum
            todaySalary.setOtPayAmount(ClockInUtil.getOtSalary(ClockInUtil.getDailySalary(otItem.getUser().getBaseSalary()),
                    otItem.getDuration(), otItem.getDayType()));
            todaySalary.setTotalPayAmount(todaySalary.getNormalPayAmount().add(todaySalary.getOtPayAmount()));

            salaryRepo.save(todaySalary);
        }

        //Return true to represent saved
        return true;
    }
}
