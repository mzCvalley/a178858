package com.fyp.a178858.service;

import com.fyp.a178858.entity.User;
import com.fyp.a178858.model.request.UserCreateRequest;
import com.fyp.a178858.model.request.UserLoginRequest;
import com.fyp.a178858.model.request.UserPasswordRequest;
import com.fyp.a178858.model.request.UserProfileEditRequest;
import com.fyp.a178858.repository.UserRepo;
import com.fyp.a178858.specification.UserSpecification;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserService {

    //Repository
    @Autowired
    UserRepo repository;

    public List<User> findAll() {
        return repository.findAll();
    }

    public User login(UserLoginRequest request) {
        Specification<User> spec = UserSpecification.build(request.getUsername(), request.getPassword());

        return repository.findOne(spec).orElseThrow();
    }

    public User create(UserCreateRequest request) {
        Specification<User> spec = UserSpecification.buildIsExist(request.getUsername());
        boolean validateUsername = repository.findOne(spec).isPresent();

        //If username exists
        if(validateUsername)
            throw new RuntimeException();

        User newUser = User.builder.instance()
                .withUsername(request.getUsername())
                .withPassword(request.getPassword())
                .withUserType(request.getUserType())
                .withName(request.getName())
                .withPhoneNumber(request.getPhoneNumber())
                .withBaseSalary(request.getBaseSalary())
                .withPosition(request.getPosition())
                .build();

        return repository.save(newUser);
    }

    public User editProfile(UserProfileEditRequest request) {
        User userItem = repository.getReferenceById(request.getId());

        userItem.setName(request.getName());
        userItem.setPhoneNumber(request.getPhoneNumber());
        userItem.setBaseSalary(request.getBaseSalary());
        userItem.setPosition(request.getPosition());
        userItem.setPassword(request.getPassword());

        return repository.save(userItem);
    }

    public User editPassword(UserPasswordRequest request) {
        Specification<User> spec = UserSpecification.build(request.getUsername(), request.getOldPassword());

        //Find user with username & old password, Else throw error
        User editPasswordUser = repository.findOne(spec).orElseThrow();

        editPasswordUser.setPassword(request.getNewPassword());

        return editPasswordUser;
    }

    public Boolean removeUser(Long id) {
        Specification<User> spec = UserSpecification.buildIsExist(id);

        //Performing deleting action
        repository.deleteById(id);

        //If id does not exist (is empty), return true (deleted);
        return repository.findOne(spec).isEmpty();
    }
}
