package com.fyp.a178858.entity;

import com.fyp.a178858.enums.UserTypeEnum;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "FYP_USER")
@Getter
@Setter
@ToString
public class User extends BaseEntity {
    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "USER_TYPE")
    private UserTypeEnum userType;
}
