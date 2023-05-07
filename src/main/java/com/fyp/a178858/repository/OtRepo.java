package com.fyp.a178858.repository;

import com.fyp.a178858.entity.Ot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface OtRepo extends JpaRepository<Ot, Long>, JpaSpecificationExecutor<Ot> {
}
