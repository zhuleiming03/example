package com.example.rabbitmq.servlet;

import com.example.rabbitmq.config.ProducerChannels;
import com.example.rabbitmq.dto.MessageDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class ProducerServlet {

    @Resource
    private ProducerChannels channels;

    public void messageProduce(MessageDTO dto) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            log.info(">>msg：{}", objectMapper.writeValueAsString(dto));
            channels.output().send(dto.asStreamMessage());
            log.info(">>queues[example.queue.test] put msg：{}",
                    objectMapper.writeValueAsString(dto));
        } catch (JsonProcessingException e) {
            log.error("msg object to json error!", e);
        }
    }
}
