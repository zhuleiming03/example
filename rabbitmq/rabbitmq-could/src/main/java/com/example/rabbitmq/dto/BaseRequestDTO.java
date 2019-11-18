package com.example.rabbitmq.dto;

import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

public class BaseRequestDTO {

    /**
     * 创建基于Stream的消息，使用固定的Header：classType & contentType
     * 如需增加Header信息，请使用 asStreamMessageBuilder()，获取messageHeaderAccessor
     */
    public Message asStreamMessage() {
        return asStreamMessageBuilder().build();
    }

    private MessageBuilder asStreamMessageBuilder() {
        return MessageBuilder.withPayload(this);
    }
}
