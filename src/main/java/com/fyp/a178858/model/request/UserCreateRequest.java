package com.fyp.a178858.model.request;

import com.fyp.a178858.enums.UserTypeEnum;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class UserCreateRequest implements Serializable {
    private String username;
    private String password;
    private UserTypeEnum userType;
    private String name;
    private String phoneNumber;
    private BigDecimal baseSalary;
    private String position;
}
