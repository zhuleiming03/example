package com.example.rabbitmq.config;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface ProducerRepeatChannels {

    String OUTPUT_TO_QUEUES = "outputToQueuesRepeat";

    @Output(OUTPUT_TO_QUEUES)
    MessageChannel output();
}
