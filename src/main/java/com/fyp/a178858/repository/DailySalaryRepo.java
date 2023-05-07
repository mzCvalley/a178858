package com.fyp.a178858.repository;

import com.fyp.a178858.entity.DailySalary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DailySalaryRepo extends JpaRepository<DailySalary, Long>, JpaSpecificationExecutor<DailySalary> {
}
