package com.fyp.a178858.controller;

import com.fyp.a178858.entity.User;
import com.fyp.a178858.service.WorkCheckInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/check_in")
public class WorkCheckInEndpoint {

    //Service
    @Autowired
    WorkCheckInService service;

    //Check if user has clocked in for today
    @GetMapping(path = "/check/{id}")
    public ResponseEntity<Boolean> validate(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();

        return new ResponseEntity<>(this.service.todayClockedIn(id), headers, HttpStatus.OK);
    }

    //If returned true, then clock in is late
    @PostMapping(path = "/clock_in/{id}")
    public ResponseEntity<Boolean> clockIn(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();

        return new ResponseEntity<>(this.service.clockIn(id), headers, HttpStatus.OK);
    }

    //If returned true, then clock out is late
    @PostMapping(path = "/clock_out/{id}")
    public ResponseEntity<Boolean> clockOut(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();

        return new ResponseEntity<>(this.service.clockOut(id), headers, HttpStatus.OK);
    }
}
