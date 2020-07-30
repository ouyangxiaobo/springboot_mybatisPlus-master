package com.springboot.tasking;

import com.springboot.entity.User;
import com.springboot.service.UserService;
import com.springboot.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * author: ouyang
 * Date:2020/7/27 20:58
 **/

public class RedisTask {

    @Autowired
    private UserService userService;



    @Autowired
    private RedisUtil redisUtil;




}
