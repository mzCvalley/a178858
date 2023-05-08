package com.fyp.a178858.model.request;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class OtCreateRequest implements Serializable {
    //Format('2023-12-25') YYYY-MM-DD
    private LocalDate otDate;
    private BigDecimal duration;
    private String jobDesc;
    private String requestUserType;
    private String dayType;
    private Long userId;
}
