package com.fyp.a178858.specification;

import com.fyp.a178858.entity.User;
import com.fyp.a178858.entity.WorkCheckIn;
import com.fyp.a178858.util.ClockInUtil;
import jakarta.persistence.criteria.Join;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import java.time.Instant;

public interface WorkCheckInSpecification extends Specification<WorkCheckIn> {
    private static Specification<WorkCheckIn> hasId(Long id) {
        return (root, query, criteriaBuilder) -> {
            if(ObjectUtils.isEmpty(id))
                return criteriaBuilder.conjunction();
            root.join("user");
            return criteriaBuilder.equal(root.get("user").get("id"), id);
        };
    }

    private static Specification<WorkCheckIn> isClockedInTodayAndNotOut() {
        return (root, query, criteriaBuilder) -> {
            Instant startOfDay = ClockInUtil.getStartOfDay();
            Instant endOfDay = ClockInUtil.getEndOfDay();

            return criteriaBuilder.and(
                    criteriaBuilder.greaterThanOrEqualTo(root.get("clockInTime"), startOfDay),
                    criteriaBuilder.lessThan(root.get("clockInTime"), endOfDay),
                    criteriaBuilder.isNull(root.get("clockOutTime"))
            );
        };
    }

    static Specification<WorkCheckIn> build(Long id) {

        return Specification.where(hasId(id))
                .and(isClockedInTodayAndNotOut());
    }
}
