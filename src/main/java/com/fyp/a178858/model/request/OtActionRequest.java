package com.fyp.a178858.model.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class OtActionRequest implements Serializable {
    //Action ID
    private Long id;
    private String requestAction;
}
