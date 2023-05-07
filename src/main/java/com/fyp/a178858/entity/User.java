package com.fyp.a178858.entity;

import com.fyp.a178858.enums.UserTypeEnum;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

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

    @Column(name = "NAME")
    private String name;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "BASE_SALARY", scale = 2)
    private BigDecimal baseSalary;

    @Column(name = "POSITION")
    private String position;


    public static final class builder {
        private Long id;
        private String username;
        private String password;
        private UserTypeEnum userType;
        private String name;
        private String phoneNumber;
        private BigDecimal baseSalary;
        private String position;

        private builder() {
        }

        public static builder instance() {
            return new builder();
        }

        public builder withId(Long id) {
            this.id = id;
            return this;
        }

        public builder withUsername(String username) {
            this.username = username;
            return this;
        }

        public builder withPassword(String password) {
            this.password = password;
            return this;
        }

        public builder withUserType(UserTypeEnum userType) {
            this.userType = userType;
            return this;
        }

        public builder withName(String name) {
            this.name = name;
            return this;
        }

        public builder withPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public builder withBaseSalary(BigDecimal baseSalary) {
            this.baseSalary = baseSalary;
            return this;
        }

        public builder withPosition(String position) {
            this.position = position;
            return this;
        }

        public User build() {
            User user = new User();
            user.setId(id);
            user.setUsername(username);
            user.setPassword(password);
            user.setUserType(userType);
            user.setName(name);
            user.setPhoneNumber(phoneNumber);
            user.setBaseSalary(baseSalary);
            user.setPosition(position);
            return user;
        }
    }
}
