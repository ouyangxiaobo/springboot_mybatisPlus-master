package com.springboot.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.springboot.entity.User;
import com.springboot.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/*
 * @ClassName
 * @Decription TOO
 * @Author HanniOvO
 * @Date 2019/8/8 13:13
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "单个用户", notes = "单个用户")
    @PostMapping("/getOneUserById")
    public User getOneUserById(@RequestParam Integer userId) {
        try {
            log.info("[单个用户]------userId=", userId);
            if (userId == null || userId == 0) {
                throw new Exception("用户ID不能为空");
            }
            User user = userService.queryOneUserById(userId);
            return user;

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return null;

    }

    @ApiOperation(value = "单个用户", notes = "单个用户")
    @PostMapping("/getOneUserByUserName")
    public User getOneUserByUserName(@RequestParam String username) {
        try {
            log.info("[单个用户]------username=", username);
            if (username.trim() == null || "".equals(username.trim())) {
                throw new Exception("用户姓名不能为空");
            }
            User user = userService.queryUserByUserName(username);
            return user;

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return null;

    }

    @ApiOperation(value = "所有用户", notes = "所有用户")
    @PostMapping("/listAllUsers")
    public List<User> listAllUsers() {
        try {
            List<User> userList = userService.queryAllUsersList();
            userList.forEach(user -> toString());
            return userList;
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return null;

    }

    @ApiOperation(value = "用户分页", notes = "用户分页")
    @PostMapping("/listAllUsersPage")
    public IPage<User> listAllUsersPage(@RequestParam Integer pageNum,@RequestParam Integer pageSize,@RequestParam String username) {
        try {
            log.info("用户分页----pageNum=", pageNum);
            log.info("用户分页----pageNum=", pageSize);
            log.info("用户分页----username=", username);

            IPage<User> userIPage = userService.queryAllUsersPage(pageNum, pageSize,username);

            return userIPage;
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return null;

    }

    @ApiOperation(value = "用户添加", notes = "用户添加")
    @PostMapping("/addUser")
    public Map<Integer, Object> addUser(@RequestBody User user) {
        try {
            log.info("用户添加----user=", user.toString());

            Map<Integer, Object> map = userService.addUser(user);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return null;

    }

    @ApiOperation(value = "用户编辑", notes = "用户编辑")
    @PostMapping("/editUser")
    public Map<Integer, Object> editUser(@RequestBody User user) {
        try {
            log.info("用户编辑----user=", user.toString());

            Map<Integer, Object> map = userService.editUser(user);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return null;

    }

    @ApiOperation(value = "用户删除", notes = "用户删除")
    @PostMapping("/delUserById")
    public Map<Integer, Object> delUserById(@RequestParam Integer userId) {
        try {
            log.info("用户编辑----userId=", userId);

            Map<Integer, Object> map = userService.deleteUserById(userId);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return null;

    }

    @ApiOperation(value = "多个用户删除", notes = "多个用户删除")
    @PostMapping("/delCheckedUserById")
    public int delCheckedUserById(@RequestParam Integer[] userIds) {
        try {
            for (Integer userId : userIds) {
                log.info("多个用户删除----userId=", userId);

            }
            int result = userService.deleteCheckedUsers(userIds);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return 0;

    }


}
