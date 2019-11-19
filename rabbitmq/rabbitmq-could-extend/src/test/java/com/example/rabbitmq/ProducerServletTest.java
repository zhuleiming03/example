package com.example.rabbitmq;

import com.example.rabbitmq.dto.MessageDTO;
import com.example.rabbitmq.servlet.ProducerServlet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProducerServletTest {

    @Resource
    private ProducerServlet producerServlet;

    @Test
    public void messageProduceTest() {
        MessageDTO dto = new MessageDTO();
        dto.setBusinessId(1564146);
        dto.setOperation(8);
        producerServlet.messageProduce(dto);

    }

}