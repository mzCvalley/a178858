package com.fyp.a178858.specification;

import com.fyp.a178858.entity.Ot;
import com.fyp.a178858.entity.User;
import com.fyp.a178858.enums.OtRequestEnum;
import com.fyp.a178858.enums.UserTypeEnum;
import jakarta.persistence.criteria.Join;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.jpa.domain.Specification;

public interface OtSpecification extends Specification<Ot> {
    private static Specification<Ot> hasUser(Long user_id) {
        return (root, query, criteriaBuilder) -> {
            if(ObjectUtils.isEmpty(user_id))
                return criteriaBuilder.conjunction();

            Join<Ot, User> userJoin = root.join("user");
            return criteriaBuilder.equal(userJoin.get("id"), user_id);
        };
    }

    private static Specification<Ot> isPending() {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("requestStatus"), OtRequestEnum.PENDING);
    }

    private static Specification<Ot> fromEmployee() {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("userType"), UserTypeEnum.EMPLOYEE);
    }

    private static Specification<Ot> ordered() {
        return (root, query, criteriaBuilder) -> {
            query.orderBy(criteriaBuilder.desc(root.get("otDate")),
                    criteriaBuilder.asc(root.get("requestStatus")));
            return criteriaBuilder.conjunction();
        };
    }

    static Specification<Ot> buildUserHistory(Long user_id) {

        return Specification.where(hasUser(user_id))
                .and(ordered());
    }

    static Specification<Ot> buildEmployee(Long user_id) {

        return Specification.where(hasUser(user_id))
                .and(isPending())
                .and(ordered());
    }

    static Specification<Ot> buildEmployer() {

        return Specification.where(isPending())
                .and(fromEmployee())
                .and(ordered());
    }
}
