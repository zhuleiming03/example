package com.example.rabbitmq.config;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;

public interface ConsumerChannels {

    String INUPT_FROM_QUEUES = "inputFromQueues";

    @Input(INUPT_FROM_QUEUES)
    MessageChannel input();
}
