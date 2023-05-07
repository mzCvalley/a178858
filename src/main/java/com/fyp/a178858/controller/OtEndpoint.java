package com.fyp.a178858.controller;

import com.fyp.a178858.service.OtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ot")
public class OtEndpoint {

    //Service
    @Autowired
    OtService service;


}
