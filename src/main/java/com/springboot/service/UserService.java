package com.springboot.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.springboot.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    /*测试单个用户*/
    User  queryOneUserById(Integer userId);

    /*测试指定用户*/
    User queryUserByUserName(String username);


    /*测试查询所有用户*/
    List<User> queryAllUsersList();

    /*测试查询所有用户并且分页查询*/
    IPage<User> queryAllUsersPage(Integer pageNum, Integer pageSize,String username);

    /*删除一个用户*/
    Map<Integer,Object> deleteUserById(Integer userId);

    /*删除多个用户*/
    int deleteCheckedUsers(Integer[] userIds);

    /*添加用户*/
    Map<Integer,Object> addUser(User user);

    /*编辑用户*/
    Map<Integer,Object> editUser(User user);


}
