package com.fyp.a178858.controller;

import com.fyp.a178858.service.DailySalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/salary")
public class DailySalaryEndpoint {

    //Service
    @Autowired
    DailySalaryService service;


}
