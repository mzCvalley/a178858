package com.fyp.a178858.specification;

import com.fyp.a178858.entity.DailySalary;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.ZoneId;

public interface SalarySpecification extends Specification<DailySalary> {
    private static Specification<DailySalary> isToday() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("recordDate"),
                LocalDate.now(ZoneId.of("Asia/Kuala_Lumpur")));
    }

    static Specification<DailySalary> build() {
        return Specification.where(isToday());
    }
}
