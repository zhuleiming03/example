package com.example.rabbitmq.config;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;

public interface ConsumerOneChannels {

    String INUPT_FROM_QUEUES = "inputFromQueuesOne";

    @Input(INUPT_FROM_QUEUES)
    MessageChannel input();
}
