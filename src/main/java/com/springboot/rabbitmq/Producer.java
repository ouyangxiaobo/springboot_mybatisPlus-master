package com.springboot.rabbitmq;

import com.springboot.entity.User;
import com.springboot.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * author: ouyang
 * Date:2020/7/30 22:32
 **/
@Component
@Slf4j
public class Producer {

    @Resource
    private AmqpTemplate rabbitTemplate;

    @Autowired
    private UserService userService;


    /*
     * @ClassName Producer
     * @Description : 生产者
     * @Return :
     * @Author : ouyang
     * @Date : 2020/7/30 22:38
    **/

    public void send() {

        List<User> userList = userService.queryAllUsersList();
        for(User user : userList){
            log.info("发送消息=="+user.toString());
        }

        this.rabbitTemplate.convertAndSend(userList);

    }
}
