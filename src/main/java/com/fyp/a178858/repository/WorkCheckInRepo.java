package com.fyp.a178858.repository;

import com.fyp.a178858.entity.WorkCheckIn;
import com.fyp.a178858.model.response.StatListResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface WorkCheckInRepo extends JpaRepository<WorkCheckIn, Long>, JpaSpecificationExecutor<WorkCheckIn> {

    @Query("SELECT COUNT(wc.clockInTime) FROM WorkCheckIn wc " +
            "WHERE MONTH(wc.clockInTime) = :month " +
            "AND wc.lateIn = true")
    Long countLateInsByMonth(@Param("month") int month);

    @Query("SELECT COUNT(wc.clockInTime) FROM WorkCheckIn wc " +
            "WHERE MONTH(wc.clockInTime) = :month " +
            "AND wc.clockInTime < :clockInTimeRule")
    Long countEarlyInsByMonth(
            @Param("month") int month,
            @Param("clockInTimeRule") Instant clockInTimeRule);

    @Query("SELECT new com.fyp.a178858.model.response.StatListResponse(u.name, u.phoneNumber, COUNT(wci.lateIn)) " +
            "FROM WorkCheckIn wci " +
            "JOIN wci.user u " +
            "WHERE MONTH(wci.clockInTime) = :month " +
            "AND wci.lateIn = true " +
            "GROUP BY u.name, u.phoneNumber")
    List<StatListResponse> countLateInByUserAndMonth(@Param("month") int month);

    @Query("SELECT new com.fyp.a178858.model.response.StatListResponse(u.name, u.phoneNumber, COUNT(wci.clockInTime)) " +
            "FROM WorkCheckIn wci " +
            "JOIN wci.user u " +
            "WHERE MONTH(wci.clockInTime) = :month " +
            "AND wci.clockInTime < :instant " +
            "GROUP BY u.name, u.phoneNumber")
    List<StatListResponse> countUsersClockedInEarlier(@Param("month") int month,
                                                      @Param("instant") Instant clockInTimeRule);

}
