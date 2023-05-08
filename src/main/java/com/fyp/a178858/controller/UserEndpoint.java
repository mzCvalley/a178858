package com.fyp.a178858.controller;

import com.fyp.a178858.entity.User;
import com.fyp.a178858.model.request.UserCreateRequest;
import com.fyp.a178858.model.request.UserLoginRequest;
import com.fyp.a178858.model.request.UserPasswordRequest;
import com.fyp.a178858.model.request.UserProfileEditRequest;
import com.fyp.a178858.service.UserService;
import jakarta.validation.Valid;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserEndpoint {

    //Service
    @Autowired
    UserService service;

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        HttpHeaders headers = new HttpHeaders();

        return new ResponseEntity<>(this.service.findAll(), headers, HttpStatus.OK);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<User> loginUser(@RequestBody UserLoginRequest request) {
        HttpHeaders headers = new HttpHeaders();

        if (ObjectUtils.isEmpty(request))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(this.service.login(request), headers, HttpStatus.OK);
    }

    @PostMapping(path = "/create")
    public ResponseEntity<User> createUser(@RequestBody @Valid UserCreateRequest request) {
        HttpHeaders headers = new HttpHeaders();

        if (ObjectUtils.isEmpty(request))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(this.service.create(request), headers, HttpStatus.OK);
    }

    @PutMapping(path = "/update_password")
    public ResponseEntity<User> updatePassword(@RequestBody UserPasswordRequest request) {
        HttpHeaders headers = new HttpHeaders();

        if (ObjectUtils.isEmpty(request))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(this.service.editPassword(request), headers, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<User> editUser(@RequestBody UserProfileEditRequest request) {
        HttpHeaders headers = new HttpHeaders();

        if (ObjectUtils.isEmpty(request))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        if (StringUtils.isEmpty(request.getName()))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        if (StringUtils.isEmpty(request.getPhoneNumber()))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        if (ObjectUtils.isEmpty(request.getBaseSalary()))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        if (StringUtils.isEmpty(request.getPosition()))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(this.service.editProfile(request), headers, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Boolean> removeUser(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();

        if (ObjectUtils.isEmpty(id))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(this.service.removeUser(id), headers, HttpStatus.OK);
    }

}
