package com.secbro.drools.web.controller;

import com.github.pagehelper.PageInfo;
import com.secbro.drools.domain.User;
import com.secbro.drools.model.UserVo;
import com.secbro.drools.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户维护
 * @program: rule-web
 * @description:
 * @author: guoqingming
 * @create: 2019-01-04 17:34
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public void add(User user) {
        userService.add(user);
    }

    @PostMapping("/update")
    public void update(User user) {
        userService.update(user);
    }

    @PostMapping("/delete")
    public void delete(Integer id) {
        userService.delete(id);
    }

    @PostMapping("/disable")
    public void disable(Integer userId) {
        userService.disableUser(userId);
    }

    @GetMapping("/list/page")
    public PageInfo<User> listPage(String username, String email, Integer pageNum, Integer pageSize) {
        return userService.listPage(username, email, pageNum, pageSize);
    }

    @GetMapping("/list")
    public List<User> list() {
        return userService.list();
    }
    @PostMapping("/enable")
    public void enable(Integer userId) {
        userService.enableUser(userId);
    }

    @PostMapping("/password/reset")
    public void resetPassword(Integer userId) {
        userService.resetPassword(userId);
    }

    @PostMapping("/password/change")
    public void resetPassword(Integer userId,String newPassword) {
        userService.changePassword(userId,newPassword);
    }

    @GetMapping("roleIds")
    public List<Integer> roleIds(Integer userId) {
        return userService.roleIds(userId);
    }
}
