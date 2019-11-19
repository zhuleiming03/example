package com.example.rabbitmq.servlet;

import com.example.rabbitmq.config.ProducerOneChannels;
import com.example.rabbitmq.config.ProducerRepeatChannels;
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
    private ProducerOneChannels oneChannels;

    @Resource
    private ProducerRepeatChannels repeatChannels;

    public void messageProduce(MessageDTO dto) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            log.info(">>msg：{}", objectMapper.writeValueAsString(dto));

            oneChannels.output().send(dto.asStreamMessage());
            log.info(">>queues[example.extend.queue.one] put msg：{}",
                    objectMapper.writeValueAsString(dto));

            repeatChannels.output().send(dto.asStreamMessage());
            log.info(">>queues[example.extend.queue.repeat] put msg：{}",
                    objectMapper.writeValueAsString(dto));

        } catch (JsonProcessingException e) {
            log.error("msg object to json error!", e);
        }
    }
}
