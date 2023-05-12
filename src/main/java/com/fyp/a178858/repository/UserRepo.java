package com.fyp.a178858.repository;

import com.fyp.a178858.entity.User;
import com.fyp.a178858.model.response.SalaryResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    @Query(value = "SELECT NEW com.fyp.a178858.model.response.SalaryResponse(u.id, u.name, u.position, SUM(o.duration), SUM(ds.otPayAmount), SUM(ds.totalPayAmount)) " +
            "FROM User u " +
            "LEFT JOIN Ot o ON u.id = o.user.id AND MONTH(o.otDate) = :month AND YEAR(o.otDate) = :year " +
            "JOIN DailySalary ds ON u.id = ds.user.id AND MONTH(ds.recordDate) = :month AND YEAR(ds.recordDate) = :year " +
            "WHERE u.userType != com.fyp.a178858.enums.UserTypeEnum.EMPLOYER " +
            "GROUP BY u.id, u.name, u.position")
    List<SalaryResponse> getUsersSalaries(int month, int year);
}
