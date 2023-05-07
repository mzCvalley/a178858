package com.fyp.a178858.service;

import com.fyp.a178858.repository.DailySalaryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DailySalaryService {

    //Repository
    @Autowired
    DailySalaryRepo repository;


}
