package com.fyp.a178858.model.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UserLoginRequest implements Serializable {
    private String username;
    private String password;

}
