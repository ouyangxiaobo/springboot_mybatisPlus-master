package com.springboot.controller;

import com.springboot.rabbitmq.Producer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * author: ouyang
 * Date:2020/7/30 22:51
 **/
@RestController
@Slf4j
@RequestMapping("/rabbitmq")
public class RabbitMqController {

    @Resource
    private Producer producer;


    @GetMapping("/send")
    public void send(){
        this.producer.send();
    }
}
