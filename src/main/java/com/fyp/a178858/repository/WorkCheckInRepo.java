package com.fyp.a178858.repository;

import com.fyp.a178858.entity.WorkCheckIn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkCheckInRepo extends JpaRepository<WorkCheckIn, Long>, JpaSpecificationExecutor<WorkCheckIn> {
}
