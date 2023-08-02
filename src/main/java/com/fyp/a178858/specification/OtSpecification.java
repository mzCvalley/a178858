package com.fyp.a178858.specification;

import com.fyp.a178858.entity.Ot;
import com.fyp.a178858.entity.User;
import com.fyp.a178858.enums.OtRequestEnum;
import com.fyp.a178858.enums.UserTypeEnum;
import com.fyp.a178858.model.request.OtSearchRequest;
import jakarta.persistence.criteria.Join;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public interface OtSpecification extends Specification<Ot> {
    private static Specification<Ot> isBetweenDate(LocalDate fromDate, LocalDate toDate) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("otDate"),fromDate,toDate);
    }

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

    private static Specification<Ot> isApproved() {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("requestStatus"), OtRequestEnum.APPROVED);
    }

    private static Specification<Ot> fromEmployee() {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("userType"), UserTypeEnum.EMPLOYEE);
    }

    private static Specification<Ot> fromEmployer() {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("userType"), UserTypeEnum.EMPLOYER);
    }

    private static Specification<Ot> ordered() {
        return (root, query, criteriaBuilder) -> {
            query.orderBy(criteriaBuilder.desc(root.get("otDate")),
                    criteriaBuilder.asc(root.get("requestStatus")));
            return criteriaBuilder.conjunction();
        };
    }

    static Specification<Ot> buildBetweenDate(OtSearchRequest request) {
        return ObjectUtils.isNotEmpty(request.getDateFrom()) && ObjectUtils.isNotEmpty(request.getDateTo()) ?
                Specification.where(isBetweenDate(request.getDateFrom(),request.getDateTo()))
                        .and(ordered()) :
                Specification.allOf();
    }

    static Specification<Ot> buildUserHistory(Long user_id) {

        return Specification.where(hasUser(user_id))
                .and(ordered());
    }

    static Specification<Ot> buildEmployee(Long user_id) {

        return Specification.where(hasUser(user_id))
                .and(isPending())
                .and(fromEmployer())
                .and(ordered());
    }

    static Specification<Ot> buildEmployer() {

        return Specification.where(isPending().and(fromEmployee()))
                .or(isApproved())
                .and(ordered());
    }
}
