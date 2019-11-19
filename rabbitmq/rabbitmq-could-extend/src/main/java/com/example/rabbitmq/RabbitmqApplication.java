package com.example.rabbitmq;

import com.example.rabbitmq.config.ConsumerOneChannels;
import com.example.rabbitmq.config.ConsumerRepeatChannels;
import com.example.rabbitmq.config.ProducerOneChannels;
import com.example.rabbitmq.config.ProducerRepeatChannels;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableBinding({ConsumerOneChannels.class, ProducerOneChannels.class,
        ConsumerRepeatChannels.class, ProducerRepeatChannels.class})
public class RabbitmqApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqApplication.class, args);
    }

}
