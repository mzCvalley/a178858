package com.fyp.a178858.specification;

import com.fyp.a178858.entity.User;
import com.fyp.a178858.model.request.UserLoginRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

public interface UserSpecification extends Specification<User> {

    static Specification<User> hasUsername(String username) {
        return (root, query, criteriaBuilder) -> {
            if(StringUtils.isEmpty(username))
                return criteriaBuilder.conjunction();
            return criteriaBuilder.equal(root.get("username"), username);
        };
    }

    static Specification<User> hasPassword(String password) {
        return (root, query, criteriaBuilder) -> {
            if(StringUtils.isEmpty(password))
                return criteriaBuilder.conjunction();
            return criteriaBuilder.equal(root.get("password"), password);
        };
    }

    static Specification<User> build(UserLoginRequest request) {
        return Specification.where(hasUsername(request.getUsername()))
                .and(hasPassword(request.getPassword()));
    }
}
