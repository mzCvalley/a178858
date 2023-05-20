package com.fyp.a178858.service;

import com.fyp.a178858.model.response.StatListResponse;
import com.fyp.a178858.model.response.StatResponse;
import com.fyp.a178858.repository.DailySalaryRepo;
import com.fyp.a178858.repository.OtRepo;
import com.fyp.a178858.repository.WorkCheckInRepo;
import com.fyp.a178858.util.RuleUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DateFormatSymbols;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
@Transactional
public class StatsService {

    //Repository
    @Autowired
    WorkCheckInRepo workCheckInRepo;
    @Autowired
    DailySalaryRepo salaryRepo;
    @Autowired
    OtRepo otRepo;

    public StatResponse getLateIn() {
        int currentMonth = LocalDate.now().getMonth().getValue();
        String[] monthNames = new DateFormatSymbols().getMonths();
        List<Long> data = new ArrayList<>();
        List<String> labels = new ArrayList<>();

        Stream.iterate(1, n -> n <= currentMonth, n -> n + 1).forEach(x -> {
            data.add(workCheckInRepo.countLateInsByMonth(x));
            labels.add(monthNames[x-1]);
        });

        return new StatResponse(data, labels);
    }

    public List<StatListResponse> getLateInUsers() {
        int currentMonth = LocalDate.now().getMonth().getValue();
        return workCheckInRepo.countLateInByUserAndMonth(currentMonth);
    }

    public StatResponse getEarlyIn() {
        int currentMonth = LocalDate.now().getMonth().getValue();
        String[] monthNames = new DateFormatSymbols().getMonths();
        Instant rule = RuleUtil.clockInTimeRule();
        List<Long> data = new ArrayList<>();
        List<String> labels = new ArrayList<>();

        Stream.iterate(1, n -> n <= currentMonth, n -> n + 1).forEach(x -> {
            data.add(workCheckInRepo.countEarlyInsByMonth(x, rule));
            labels.add(monthNames[x-1]);
        });

        return new StatResponse(data, labels);
    }

    public List<StatListResponse> getEarlyInUsers() {
        int currentMonth = LocalDate.now().getMonth().getValue();
        Instant rule = RuleUtil.clockInTimeRule();
        return workCheckInRepo.countUsersClockedInEarlier(currentMonth, rule);
    }

    public StatResponse getTotalOtRequest() {
        int currentMonth = LocalDate.now().getMonth().getValue();
        String[] monthNames = new DateFormatSymbols().getMonths();
        List<Long> data = new ArrayList<>();
        List<String> labels = new ArrayList<>();

        Stream.iterate(1, n -> n <= currentMonth, n -> n + 1).forEach(x -> {
            data.add(otRepo.countTotalOtByMonth(x));
            labels.add(monthNames[x-1]);
        });

        return new StatResponse(data, labels);
    }

    public StatResponse getTotalDailySalary() {
        LocalDate currentDate = LocalDate.now();
        List<Long> data = new ArrayList<>();
        List<String> labels = new ArrayList<>();

        Stream.iterate(12, n -> n > 0, n -> n - 1).forEach(x -> {
            BigDecimal sumSalary = salaryRepo.sumTotalSalaries(currentDate.minusDays(x));
            data.add(sumSalary == null ? 0L : sumSalary.longValue());
            labels.add((currentDate.minusDays(x)).toString());
        });

        return new StatResponse(data, labels);
    }
}
