package com.fyp.a178858.model.request;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class OtSearchRequest implements Serializable {
    private LocalDate dateFrom;
    private LocalDate dateTo;
}
