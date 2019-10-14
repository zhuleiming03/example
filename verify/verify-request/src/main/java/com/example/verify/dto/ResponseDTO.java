package com.example.verify.dto;

import lombok.Data;

@Data
public class ResponseDTO {

    private Integer status;

    private Integer code;

    private String message;
}