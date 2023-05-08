package com.fyp.a178858.util;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class RuleUtil {

    //ClockIn at 9am
    public static Instant clockInTimeRule() {
        ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of("Asia/Kuala_Lumpur"))
                .withHour(9)
                .withMinute(0)
                .withSecond(0)
                .withNano(0);
        return zdt.toInstant();
    }

    //ClockOut at 6pm
    public static Instant clockOutTimeRule() {
        ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of("Asia/Kuala_Lumpur"))
                .withHour(17)
                .withMinute(0)
                .withSecond(0)
                .withNano(0);
        return zdt.toInstant();
    }
}
