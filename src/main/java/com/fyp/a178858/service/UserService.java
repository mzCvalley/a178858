package com.fyp.a178858.service;

import com.fyp.a178858.entity.User;
import com.fyp.a178858.model.request.UserLoginRequest;
import com.fyp.a178858.repository.UserRepo;
import com.fyp.a178858.specification.UserSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    //Repository
    @Autowired
    UserRepo repository;

    public User login(UserLoginRequest request) {
        Specification<User> spec = UserSpecification.build(request);

        return repository.findOne(spec).orElseThrow();
    }
}
