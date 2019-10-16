package com.example.net.dto;

import lombok.Data;

@Data
public class BaseResponseDTO {

    private String status;

    private String code;

    private String message;
}
