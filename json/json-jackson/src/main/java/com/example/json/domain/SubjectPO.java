package com.example.json.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class SubjectPO {

    private Long billItemId;

    private Integer billId;

    private BigDecimal amount;

    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createTime;

    //    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
//    @JsonSerialize(using = LocalDateTimeSerializer.class)
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
//    @JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "GMT+8")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDate billDay;
}
