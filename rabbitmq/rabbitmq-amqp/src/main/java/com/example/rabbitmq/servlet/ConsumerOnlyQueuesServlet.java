package com.example.rabbitmq.servlet;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@Service
@Slf4j
public class ConsumerOnlyQueuesServlet {

    @RabbitListener(queues = "ProcessFinish")
    public void routerHandler(Message message) {
        String body = new String(message.getBody());
        log.info(">>queues[ProcessFinish] get msg：{}", body);
        // https://blog.csdn.net/weixin_38380858/article/details/84258507
    }

}
