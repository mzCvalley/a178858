package com.fyp.a178858.service;

import com.fyp.a178858.model.response.SalaryResponse;
import com.fyp.a178858.repository.DailySalaryRepo;
import com.fyp.a178858.repository.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class DailySalaryService {

    //Repository
    @Autowired
    DailySalaryRepo repository;
    @Autowired
    UserRepo userRepo;

    public List<SalaryResponse> findAllSalaryOfCurrentMonth() {
        return userRepo.getUsersSalaries(LocalDate.now().getMonth().getValue(), LocalDate.now().getYear());
    }

    public SalaryResponse findOneSalaryOfCurrentMonth(Long userId) {
        return userRepo.getUsersSalaries(LocalDate.now().getMonth().getValue(), LocalDate.now().getYear()).stream()
                .filter(salaryResponse -> salaryResponse.getId().equals(userId)).findFirst().orElseThrow();
    }
}
