package com.example.rabbitmq.config;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;

public interface ConsumerRepeatChannels {

    String INUPT_FROM_QUEUES = "inputFromQueuesRepeat";

    @Input(INUPT_FROM_QUEUES)
    MessageChannel input();
}
