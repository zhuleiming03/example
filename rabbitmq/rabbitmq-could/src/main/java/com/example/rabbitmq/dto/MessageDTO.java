package com.example.rabbitmq.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class MessageDTO extends BaseRequestDTO {

    /**
     * 订单号
     */
    private Integer businessId;

    /**
     * 0:default 解冻+清数据 1：仅解冻 2：仅清数据
     */
    private Integer operation;
}