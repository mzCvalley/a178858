package com.fyp.a178858.repository;

import com.fyp.a178858.entity.DailySalary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;

@Repository
public interface DailySalaryRepo extends JpaRepository<DailySalary, Long>, JpaSpecificationExecutor<DailySalary> {

    @Query("SELECT SUM(ds.totalPayAmount) FROM DailySalary ds " +
            "WHERE ds.recordDate = :recordDate")
    BigDecimal sumTotalSalaries(@Param("recordDate") LocalDate recordDate);

}
