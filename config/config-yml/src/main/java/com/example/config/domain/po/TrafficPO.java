package com.example.config.domain.po;

import lombok.Data;

import java.util.Date;

@Data
public class TrafficPO {

    private String trafficType;

    private Date buyDate;

    private Double fee;
}
