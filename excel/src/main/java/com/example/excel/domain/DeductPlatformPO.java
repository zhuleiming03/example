package com.example.excel.domain;

import lombok.Data;

import java.util.Date;

@Data
public class DeductPlatformPO {

    private String platformEname;

    private String platformCname;

    private Integer receiveType;

    private Date createtime;

    private Boolean valid;
}

