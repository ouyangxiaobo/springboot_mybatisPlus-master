package com.springboot.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;



/**
 * author: ouyang
 * Date:2020/7/30 22:31
 **/
@Configuration
public class RabbitMqConfig {

    /**
     * 声明一个名为simple的队列
     */
    @Bean
    public Queue testQueue() {
        return new Queue("simple");
    }
}
