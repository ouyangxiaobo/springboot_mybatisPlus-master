package com.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springboot.entity.User;
import com.springboot.mapper.UserMapper;
import com.springboot.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User queryOneUserById(Integer userId) {
        User user=userMapper.selectById(userId);
        return user;
    }

    @Override
    public User queryUserByUserName(String username) {
        User user=userMapper.selectUserByUserName(username);

        return user;
    }

    @Override
    public List<User> queryAllUsersList() {
        List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);
        return userList;
    }

    @Override
    public IPage<User> queryAllUsersPage(Integer pageNum,Integer pageSize,String username) {
        if(pageNum==null || pageNum==0){
            pageNum=1;
        }
        if(pageSize==null || pageSize==0){
            pageSize=2;
        }

        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        if(username !=null ){
            queryWrapper.like("username", username);
        }


        Page<User> userPage=new Page<User>(pageNum,pageSize);
        IPage<User> selectPage =userMapper.selectPage(userPage,queryWrapper);
        log.info("总条数="+userPage.getTotal());
        log.info("当前页="+userPage.getCurrent());
        log.info("总页码="+userPage.getPages());
        log.info("每页多少条="+userPage.getSize());
        log.info("是否有上一页="+userPage.hasPrevious());
        log.info("是否有下一页="+userPage.hasNext());

        return selectPage;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<Integer, Object> deleteUserById(Integer userId) {
        Map<Integer,Object> map=new HashMap<>();
        if(userId==null || userId==0){
            map.put(-1,"用户ID不能为空");
            return map;
        }
        User user=userMapper.selectById(userId);
        if(user==null){
            map.put(-2,"用户不存在");
            return map;
        }
        int result=userMapper.deleteById(userId);
        if(result>0){
            map.put(1,"删除用户成功......");
            return map;
        }else {
            map.put(0,"删除用户失败......");
            return map;
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteCheckedUsers(Integer[] userIds) {
        for (Integer userId: userIds){
            log.info("用户Id="+userId);
          int result=userMapper.deleteById(userId);
          if(result>0){
              log.info("用户删除成功......");
          }else {
              log.error("用户删除失败......");
          }
        }
        return 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<Integer, Object> addUser(User user) {
        Map<Integer,Object> map=new HashMap<>();
        if(user==null){
            map.put(-1,"用户信息不能为空......");
            return map;
        }
        User userData=userMapper.selectUserByUserName(user.getUsername());
        if(userData!=null){
            map.put(-2,"该用户名已存在......");
            return map;
        }
        user.setBirthday(new Date());
        int result=userMapper.insert(user);
        if(result>0){
            log.info("添加用户成功...user="+user.toString());
            map.put(1,"添加用户成功......");
            return map;
        }else {
            map.put(0,"添加用户失败......");
            return map;
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<Integer, Object> editUser(User user) {

        Map<Integer,Object> map=new HashMap<>();
        if(user==null){
            map.put(-1,"用户信息不能为空......");
            return map;
        }
        int result=userMapper.updateById(user);
        if(result>0){
            log.info("编辑用户成功...user="+user.toString());
            map.put(1,"编辑用户成功......");
            return map;
        }else {
            map.put(0,"编辑用户失败......");
            return map;
        }
    }
}
