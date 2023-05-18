package com.fyp.a178858.model.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class StatResponse implements Serializable {
    private List<Long> data;
    private List<String> labels;
    public StatResponse(List<Long> data, List<String> labels) {
        this.data = data;
        this.labels = labels;
    }
}
