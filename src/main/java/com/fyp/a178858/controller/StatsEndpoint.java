package com.fyp.a178858.controller;

import com.fyp.a178858.model.response.StatListResponse;
import com.fyp.a178858.model.response.StatResponse;
import com.fyp.a178858.service.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stats")
public class StatsEndpoint {

    @Autowired
    StatsService service;

    @GetMapping(path = "/late_in")
    public ResponseEntity<StatResponse> getLateIns() {
        HttpHeaders headers = new HttpHeaders();

        return new ResponseEntity<>(this.service.getLateIn(), headers, HttpStatus.OK);
    }

    @GetMapping(path = "/late_in/list")
    public ResponseEntity<List<StatListResponse>> getLateInUsers() {
        HttpHeaders headers = new HttpHeaders();

        return new ResponseEntity<>(this.service.getLateInUsers(), headers, HttpStatus.OK);
    }

    @GetMapping(path = "/early_in")
    public ResponseEntity<StatResponse> getEarlyIns() {
        HttpHeaders headers = new HttpHeaders();

        return new ResponseEntity<>(this.service.getEarlyIn(), headers, HttpStatus.OK);
    }

    @GetMapping(path = "/early_in/list")
    public ResponseEntity<List<StatListResponse>> getEarlyInUsers() {
        HttpHeaders headers = new HttpHeaders();

        return new ResponseEntity<>(this.service.getEarlyInUsers(), headers, HttpStatus.OK);
    }

    @GetMapping(path = "/ot_request")
    public ResponseEntity<StatResponse> getTotalOtRequests() {
        HttpHeaders headers = new HttpHeaders();

        return new ResponseEntity<>(this.service.getTotalOtRequest(), headers, HttpStatus.OK);
    }

    @GetMapping(path = "/daily_salary")
    public ResponseEntity<StatResponse> getTotalDailySalaries() {
        HttpHeaders headers = new HttpHeaders();

        return new ResponseEntity<>(this.service.getTotalDailySalary(), headers, HttpStatus.OK);
    }
}
