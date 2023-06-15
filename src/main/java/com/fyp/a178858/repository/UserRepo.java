package com.fyp.a178858.repository;

import com.fyp.a178858.entity.User;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    @Query(value = "SELECT u.id, u.name, u.`position` , (ot.duration), SUM(ds.ot_pay_amount) AS TotalOtPay, " +
            "SUM(ds.total_pay_amount) AS TotalPay " +
            "FROM fyp_user u " +
            "LEFT JOIN (" +
            "SELECT employeee_user_id, SUM(o.duration) AS duration FROM fyp_ot o " +
            "WHERE MONTH(o.ot_date) = :month AND YEAR(o.ot_date) = :year AND o.request_status = 'COMPLETED') ot " +
            "ON u.id = ot.employeee_user_id " +
            "JOIN fyp_daily_salary ds ON u.id = ds.employeee_user_id " +
            "AND MONTH(ds.record_date) = :month AND YEAR(ds.record_date) = :year " +
            "WHERE u.user_type != 'EMPLOYER' " +
            "GROUP BY u.id;", nativeQuery = true)
    List<Tuple> getUsersSalaries(@Param("month") int month, @Param("year") int year);
}
