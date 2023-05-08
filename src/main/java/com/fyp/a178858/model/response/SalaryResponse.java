package com.fyp.a178858.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class SalaryResponse implements Serializable {
    private Long id;
    private String name;
    private String position;
    private BigDecimal totalOtDuration;
    private BigDecimal totalOtPay;
    private BigDecimal totalPay;

    SalaryResponse(Long id, String name, String position, BigDecimal totalDuration, BigDecimal totalOtPay, BigDecimal totalPay) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.totalOtDuration = totalDuration;
        this.totalOtPay = totalOtPay;
        this.totalPay = totalPay;
    }
}
