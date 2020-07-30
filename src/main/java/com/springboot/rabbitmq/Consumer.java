package com.springboot.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * author: ouyang
 * Date:2020/7/30 22:41
 **/
@Component
@Slf4j
public class Consumer {

    @RabbitListener(queues = "simple")
    public void receive(String message) {
        log.info("消费者1收到消息：{}", message);
    }
}
