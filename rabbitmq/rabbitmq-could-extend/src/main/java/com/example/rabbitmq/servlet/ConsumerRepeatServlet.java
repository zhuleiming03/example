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

@Slf4j
@Service
public class ConsumerRepeatServlet {

    // 异常时会重试maxAttempts次数后线程和消息同时别占用，状态unacked,
    // 当连接释放后，消息重回队列，同时dlq会保存信息
    // 当线程被占用达到concurrency上限时，程序不再消费信息
    @StreamListener(value = ConsumerRepeatChannels.INUPT_FROM_QUEUES)
    public void messageConsumer(Message<MessageDTO> message,
                                @Header(AmqpHeaders.CHANNEL) Channel channel,
                                @Header(AmqpHeaders.DELIVERY_TAG) Long deliveryTag) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        MessageDTO dto = message.getPayload();
        log.info(">>queues[example.extend.queue.repeat] get msg：{}",
                objectMapper.writeValueAsString(dto));
        throw new Exception("BusinessId:" + dto.getBusinessId());
        //channel.basicAck(deliveryTag, false); //数据操作完需要确认
    }
}
