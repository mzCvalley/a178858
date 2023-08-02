package com.fyp.a178858.service;

import com.fyp.a178858.entity.DailySalary;
import com.fyp.a178858.repository.DailySalaryRepo;
import com.fyp.a178858.specification.SalarySpecification;
import com.fyp.a178858.specification.WorkCheckInSpecification;
import com.fyp.a178858.util.ClockInUtil;
import com.fyp.a178858.util.RuleUtil;
import com.fyp.a178858.entity.User;
import com.fyp.a178858.entity.WorkCheckIn;
import com.fyp.a178858.repository.UserRepo;
import com.fyp.a178858.repository.WorkCheckInRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

@Service
@Transactional
public class WorkCheckInService {

    //Repository
    @Autowired
    WorkCheckInRepo repository;
    @Autowired
    UserRepo userRepo;
    @Autowired
    DailySalaryRepo salaryRepo;

    public Boolean todayClockedIn(Long id) {
        Specification<WorkCheckIn> spec = WorkCheckInSpecification.build(id);

        return repository.findAll(spec).stream().findFirst().isEmpty();
    }

    public Boolean clockIn(Long id) {
        User userItem = userRepo.getReferenceById(id);

        //If user has clocked in or yet to clock out for the day, return true
        if(!todayClockedIn(id))
            return todayClockedIn(id);

//        System.out.println(ZonedDateTime.now());

        //For Checking If Late Clock In
        Instant clockInRule = RuleUtil.clockInTimeRule();
        Instant currentInstant = ClockInUtil.getCurrentInstant();

        WorkCheckIn newCheckIn = WorkCheckIn.builder.instance()
                .withClockInTime(currentInstant)
                .withLateIn(currentInstant.isAfter(clockInRule))
                .withUser(userItem)
                .build();

        repository.save(newCheckIn);

        return currentInstant.isAfter(clockInRule);
    }

    public Boolean clockOut(Long id) {
        User userItem = userRepo.getReferenceById(id);
        Specification<WorkCheckIn> spec = WorkCheckInSpecification.build(id);

        //If user has clocked out or yet to clock in for the day, return true
        if(todayClockedIn(id))
            return todayClockedIn(id);

        WorkCheckIn todayCheckIn = repository.findAll(spec).stream().findFirst().orElseThrow();

//        System.out.println(ZonedDateTime.now());

        //For Checking If Late Clock Out
        Instant clockOutRule = RuleUtil.clockOutTimeRule();
        Instant currentInstant = ClockInUtil.getCurrentInstant();

        //Find the total working hours
        Duration duration = Duration.between(todayCheckIn.getClockInTime(), currentInstant);
        BigDecimal totalHours = new BigDecimal(Math.max(0, duration.toHours()));

        todayCheckIn.setClockOutTime(currentInstant);
        todayCheckIn.setLateOut(currentInstant.isAfter(clockOutRule));
        todayCheckIn.setTotalWorkHours(totalHours);

        repository.save(todayCheckIn);

        //Find record for today or create one if NOT FOUND
        Specification<DailySalary> salarySpec = SalarySpecification.build(id,
                LocalDate.now(ZoneId.of("Asia/Kuala_Lumpur")));
        DailySalary todaySalary = salaryRepo.findAll(salarySpec).stream().findFirst()
                .orElseGet(() -> salaryRepo.save(DailySalary.builder.instance()
                        .withUser(userItem)
                        .withRecordDate(LocalDate.now(ZoneId.of("Asia/Kuala_Lumpur")))
                        .withNormalPayAmount(BigDecimal.ZERO)
                        .withOtPayAmount(BigDecimal.ZERO)
                        .withTotalPayAmount(BigDecimal.ZERO)
                        .build()));

        //Get Daily Salary & Add to Sum
        todaySalary.setNormalPayAmount(ClockInUtil.getDailySalary(userItem.getBaseSalary()));
        todaySalary.setTotalPayAmount(todaySalary.getNormalPayAmount().add(todaySalary.getOtPayAmount()));

        salaryRepo.save(todaySalary);

        return currentInstant.isAfter(clockOutRule);
    }
}
