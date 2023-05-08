package com.fyp.a178858.controller;

import com.fyp.a178858.entity.Ot;
import com.fyp.a178858.model.request.OtActionRequest;
import com.fyp.a178858.model.request.OtCreateRequest;
import com.fyp.a178858.service.OtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ot")
public class OtEndpoint {

    //Service
    @Autowired
    OtService service;

    @GetMapping(path = "/{user_id}")
    public ResponseEntity<List<Ot>> findByUser(@PathVariable Long user_id) {
        HttpHeaders headers = new HttpHeaders();

        return new ResponseEntity<>(this.service.findByUser(user_id), headers, HttpStatus.OK);
    }

    @GetMapping(path = "/employer")
    public ResponseEntity<List<Ot>> employerFindAll() {
        HttpHeaders headers = new HttpHeaders();

        return new ResponseEntity<>(this.service.employerFindAll(), headers, HttpStatus.OK);
    }

    @GetMapping(path = "/employee/{user_id}")
    public ResponseEntity<List<Ot>> employeeFindAll(@PathVariable Long user_id) {
        HttpHeaders headers = new HttpHeaders();

        return new ResponseEntity<>(this.service.employeeFindAll(user_id), headers, HttpStatus.OK);
    }

    @PostMapping(path = "/request")
    public ResponseEntity<Boolean> requestOt(@RequestBody OtCreateRequest request) {
        HttpHeaders headers = new HttpHeaders();

        return new ResponseEntity<>(this.service.requestOt(request), headers, HttpStatus.OK);
    }

    @PutMapping(path = "/action")
    public ResponseEntity<Boolean> actionOt(@RequestBody OtActionRequest request) {
        HttpHeaders headers = new HttpHeaders();

        return new ResponseEntity<>(this.service.actionOt(request), headers, HttpStatus.OK);
    }
}
