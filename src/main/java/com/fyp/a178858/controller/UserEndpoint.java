package com.fyp.a178858.controller;

import com.fyp.a178858.entity.User;
import com.fyp.a178858.model.request.UserLoginRequest;
import com.fyp.a178858.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserEndpoint {

    //Service
    @Autowired
    UserService service;

    @PostMapping(path = "/login")
    public ResponseEntity<User> loginUser(@RequestBody UserLoginRequest request) {
        HttpHeaders headers = new HttpHeaders();

        if (ObjectUtils.isEmpty(request)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(this.service.login(request), headers, HttpStatus.CREATED);
    }
}
