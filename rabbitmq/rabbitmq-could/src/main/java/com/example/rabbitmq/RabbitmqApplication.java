package com.example.rabbitmq;

import com.example.rabbitmq.config.ConsumerChannels;
import com.example.rabbitmq.config.ProducerChannels;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableBinding({ConsumerChannels.class, ProducerChannels.class})
public class RabbitmqApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqApplication.class, args);
    }

}
