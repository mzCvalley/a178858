package com.fyp.a178858.controller;

import com.fyp.a178858.service.WorkCheckInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/check_in")
public class WorkCheckInEndpoint {

    //Service
    @Autowired
    WorkCheckInService service;


}
