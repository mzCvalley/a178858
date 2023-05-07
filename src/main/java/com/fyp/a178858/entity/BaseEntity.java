package com.fyp.a178858.entity;

import jakarta.persistence.*;
import lombok.*;

@MappedSuperclass
@Getter
@Setter
@ToString
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

}
