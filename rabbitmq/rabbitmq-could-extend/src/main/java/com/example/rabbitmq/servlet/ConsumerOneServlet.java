package com.example.rabbitmq.servlet;

import com.example.rabbitmq.config.ConsumerOneChannels;
import com.example.rabbitmq.config.ConsumerRepeatChannels;
import com.example.rabbitmq.dto.MessageDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class ConsumerOneServlet {

    @StreamListener(value = ConsumerOneChannels.INUPT_FROM_QUEUES)
    public void messageConsumer(Message<MessageDTO> message,
                                @Header(AmqpHeaders.CHANNEL) Channel channel,
                                @Header(AmqpHeaders.DELIVERY_TAG) Long deliveryTag) throws IOException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            MessageDTO dto = message.getPayload();
            log.info(">>queues[example.extend.queue.one] get msg：{}",
                    objectMapper.writeValueAsString(dto));
            throw new Exception("BusinessId:" + dto.getBusinessId());

        } catch (Exception e) {
            log.error(e.toString());
        } finally {
            //异常直接结束，不再重试，信息丢掉
            channel.basicAck(deliveryTag, false);
        }
    }
}
