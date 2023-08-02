package com.fyp.a178858.specification;

import com.fyp.a178858.entity.DailySalary;
import com.fyp.a178858.entity.User;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public interface SalarySpecification extends Specification<DailySalary> {
    private static Specification<DailySalary> isDate(LocalDate date) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("recordDate"),
                date);
    }

    private static Specification<DailySalary> isUser(Long userId) {
        return (root, query, criteriaBuilder) -> {
            Join<DailySalary, User> userJoin = root.join("user");
            return criteriaBuilder.equal(userJoin.get("id"), userId);
        };
    }

    static Specification<DailySalary> build(Long userId, LocalDate date) {
        return Specification.where(isDate(date))
                        .and(isUser(userId));
    }
}
