package com.fyp.a178858.util;

import com.fyp.a178858.enums.DayTypeEnum;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.*;
import java.util.stream.IntStream;

public class ClockInUtil {
    public static Instant getCurrentInstant() {
        return ZonedDateTime.now(ZoneId.of("Asia/Kuala_Lumpur")).toInstant();
    }

    public static Instant getStartOfDay() {
        return LocalDate.now().atStartOfDay().atZone(ZoneId.of("Asia/Kuala_Lumpur")).toInstant();
    }

    public static Instant getEndOfDay() {
        return LocalDate.now().plusDays(1).atStartOfDay().atZone(ZoneId.of("Asia/Kuala_Lumpur")).toInstant();
    }

    public static BigDecimal getDailySalary(BigDecimal salary) {
        // Get the current month and year
        YearMonth currentMonth = YearMonth.now();

        // Get the number of days in the month
        int numDays = currentMonth.lengthOfMonth();

        // Initialize a counter for the weekdays
        int numWeekdays = (int) IntStream.rangeClosed(1, numDays)
                .mapToObj(currentMonth::atDay).map(LocalDate::getDayOfWeek)
                .filter(dow -> dow != DayOfWeek.SATURDAY && dow != DayOfWeek.SUNDAY).count();

        return salary.divide(new BigDecimal(numWeekdays), 2, RoundingMode.HALF_UP);
    }

    public static BigDecimal getOtSalary(BigDecimal dailySalary, BigDecimal otHours, DayTypeEnum dayType) {
        BigDecimal hourlyRate = dailySalary.divide(new BigDecimal("8"), 2, RoundingMode.HALF_UP);

        switch (dayType) {
            case NORMAL -> hourlyRate = hourlyRate.multiply(new BigDecimal("1.5"));
            case REST -> hourlyRate = hourlyRate.multiply(new BigDecimal("2"));
            case HOLIDAY -> hourlyRate = hourlyRate.multiply(new BigDecimal("3"));
        }

        return hourlyRate.multiply(otHours);
    }
}
