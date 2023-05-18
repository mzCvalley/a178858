package com.fyp.a178858.repository;

import com.fyp.a178858.entity.Ot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OtRepo extends JpaRepository<Ot, Long>, JpaSpecificationExecutor<Ot> {

    @Query("SELECT COUNT(ot) FROM Ot ot " +
            "WHERE MONTH(ot.otDate) = :month ")
    Long countTotalOtByMonth(@Param("month") int month);

}
