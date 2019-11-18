package com.example.rabbitmq.servlet;

import com.example.rabbitmq.config.ConsumerChannels;
import com.example.rabbitmq.dto.MessageDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ConsumerServlet {

    @StreamListener(value = ConsumerChannels.INUPT_FROM_QUEUES)
    public void messageConsumer(Message<MessageDTO> message) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            MessageDTO dto = message.getPayload();
            log.info(">>queues[example.queue.test] get msg：{}",
                    objectMapper.writeValueAsString(dto));
            System.out.println(">>msg:" + dto.toString());
        } catch (JsonProcessingException e) {
            log.error("msg object to json error!", e);
        }
    }
}
