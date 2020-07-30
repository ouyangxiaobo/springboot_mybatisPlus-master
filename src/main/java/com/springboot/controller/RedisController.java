package com.springboot.controller;

import com.alibaba.fastjson.JSON;
import com.springboot.entity.User;
import com.springboot.service.UserService;
import com.springboot.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * author: ouyang
 * Date:2020/7/27 21:04
 **/
@RestController
@RequestMapping("/redis")
public class RedisController {


    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * @ClassName RedisController
     * @Description : 测试redis 存入集合对象
     * @Return : Object
     * @Author : ouyang
     * @Date : 2020/7/27 22:42
     **/
    @GetMapping("/setUserCaChe")
    public Object setUserCaChe() {
        List<User> userList = userService.queryAllUsersList();

        for (User user : userList) {



            redisUtil.hset("user", user.getId() + "", JSON.toJSON(user.toString()));


        }

        return userList;

    }


    /**
     * @ClassName RedisController
     * @Description : 测试redis根据key获取value
     * @Return : Object
     * @Author : ouyang
     * @Date : 2020/7/27 22:43
    **/
    @PostMapping("/getUserCaChe")
    public Object getUserCaChe(@RequestParam Integer id) {
        Object o = redisUtil.hget("user", id + "");
        System.out.println(id);
        return o;

    }


}
