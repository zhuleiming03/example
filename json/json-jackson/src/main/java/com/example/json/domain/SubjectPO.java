package com.example.json.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SubjectPO {

    private Integer billItemID;

    private Integer billID;

    private BigDecimal amount;

    private String name;
}
