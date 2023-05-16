package com.fyp.a178858.model.request;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class UserProfileEditRequest implements Serializable {
    private Long id;
    private String name;
    private String phoneNumber;
    private BigDecimal baseSalary;
    private String position;
    private String password;
}
