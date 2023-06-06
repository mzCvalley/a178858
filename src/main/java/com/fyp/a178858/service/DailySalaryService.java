package com.fyp.a178858.service;

import com.fyp.a178858.model.response.SalaryResponse;
import com.fyp.a178858.repository.DailySalaryRepo;
import com.fyp.a178858.repository.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DailySalaryService {

    //Repository
    @Autowired
    DailySalaryRepo repository;
    @Autowired
    UserRepo userRepo;

    public List<SalaryResponse> findAllSalaryOfCurrentMonth() {
        return userRepo.getUsersSalaries(LocalDate.now().getMonth().getValue(), LocalDate.now().getYear()).stream().map(tuple -> {
            Long id = tuple.get("id", Long.class);
            String name = tuple.get("name", String.class);
            String position = tuple.get("position", String.class);
            BigDecimal duration = tuple.get("duration", BigDecimal.class);
            BigDecimal totalOtPay = tuple.get("TotalOtPay", BigDecimal.class);
            BigDecimal totalPay = tuple.get("TotalPay", BigDecimal.class);

            return new SalaryResponse(id, name, position, duration, totalOtPay, totalPay);
        }).collect(Collectors.toList());
    }

    public SalaryResponse findOneSalaryOfCurrentMonth(Long userId) {
        return userRepo.getUsersSalaries(LocalDate.now().getMonth().getValue(), LocalDate.now().getYear()).stream()
                .filter(salaryResponse -> salaryResponse.get("id").equals(userId)).findFirst().map(tuple -> {
                    Long id = tuple.get("id", Long.class);
                    String name = tuple.get("name", String.class);
                    String position = tuple.get("position", String.class);
                    BigDecimal duration = tuple.get("duration", BigDecimal.class);
                    BigDecimal totalOtPay = tuple.get("TotalOtPay", BigDecimal.class);
                    BigDecimal totalPay = tuple.get("TotalPay", BigDecimal.class);

                    return new SalaryResponse(id, name, position, duration, totalOtPay, totalPay);
                }).orElseThrow();
    }
}
