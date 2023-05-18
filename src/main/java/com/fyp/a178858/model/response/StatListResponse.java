package com.fyp.a178858.model.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class StatListResponse implements Serializable {
    private String employeeName;
    private String phoneNumber;
    private Long count;

    public StatListResponse(String employeeName, String phoneNumber, Long count) {
        this.employeeName = employeeName;
        this.phoneNumber = phoneNumber;
        this.count = count;
    }
}
