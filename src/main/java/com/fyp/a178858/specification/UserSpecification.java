package com.fyp.a178858.specification;

import com.fyp.a178858.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

public interface UserSpecification extends Specification<User> {

    private static Specification<User> hasId(Long id) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("id"), id);
        };
    }

    private static Specification<User> hasUsername(String username) {
        return (root, query, criteriaBuilder) -> {
            if(StringUtils.isEmpty(username))
                return criteriaBuilder.conjunction();
            return criteriaBuilder.equal(root.get("username"), username);
        };
    }

    private static Specification<User> hasPassword(String password) {
        return (root, query, criteriaBuilder) -> {
            if(StringUtils.isEmpty(password))
                return criteriaBuilder.conjunction();
            return criteriaBuilder.equal(root.get("password"), password);
        };
    }

    private static Specification<User> hasPosition(String position) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("position"), position);
    }

    static Specification<User> build(String username, String password) {

        return Specification.where(hasUsername(username))
                .and(hasPassword(password));
    }

    static Specification<User> buildIsExist(Object object) {
        if(object instanceof Long)
            return Specification.where(hasId((Long) object));
        if(object instanceof String)
            return Specification.where(hasUsername((String) object));

        return Specification.where((root, query, criteriaBuilder) ->
                criteriaBuilder.disjunction());
    }

    static Specification<User> buildHasPosition(String position) {
        return StringUtils.isNotEmpty(position) ?
                Specification.where(hasPosition(position)) :
                Specification.allOf();
    }

}
