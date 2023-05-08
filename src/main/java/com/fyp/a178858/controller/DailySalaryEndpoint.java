package com.fyp.a178858.controller;

import com.fyp.a178858.model.response.SalaryResponse;
import com.fyp.a178858.service.DailySalaryService;
import com.fyp.a178858.util.CSVUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/salary")
public class DailySalaryEndpoint {

    //Service
    @Autowired
    DailySalaryService service;

    @GetMapping
    public ResponseEntity<List<SalaryResponse>> findAllSalaryOfCurrentMonth() throws IOException {
        HttpHeaders headers = new HttpHeaders();

        return new ResponseEntity<>(service.findAllSalaryOfCurrentMonth(), headers, HttpStatus.OK);
    }

    @GetMapping(path = "/csv")
    public ResponseEntity<Resource> findAllSalaryOfCurrentMonthCSV() throws IOException {
        List<SalaryResponse> salaries = service.findAllSalaryOfCurrentMonth();
        ByteArrayInputStream in = CSVUtil.salaryResponsesToCSV(salaries);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=salaries.csv");
        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType("text/csv"))
                .body(new InputStreamResource(in));
    }
}
