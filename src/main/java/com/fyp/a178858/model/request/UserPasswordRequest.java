package com.fyp.a178858.model.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserPasswordRequest implements Serializable {
    private String username;
    private String oldPassword;
    private String newPassword;
}
