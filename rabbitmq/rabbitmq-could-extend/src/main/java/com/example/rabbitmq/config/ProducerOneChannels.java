package com.example.rabbitmq.config;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface ProducerOneChannels {

    String OUTPUT_TO_QUEUES = "outputToQueuesOne";

    @Output(OUTPUT_TO_QUEUES)
    MessageChannel output();
}
