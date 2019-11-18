package com.example.rabbitmq.config;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface ProducerChannels {

    String OUTPUT_TO_QUEUES = "outputToQueues";

    @Output(OUTPUT_TO_QUEUES)
    MessageChannel output();
}
