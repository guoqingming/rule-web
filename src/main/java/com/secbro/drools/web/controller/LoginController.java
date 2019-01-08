package com.secbro.drools.web.controller;

import com.secbro.drools.shiro.ShiroKit;
import com.secbro.drools.shiro.ShiroUser;
import com.secbro.drools.utils.ToolUtil;
import com.secbro.drools.utils.WebUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: rule-web
 * @description:
 * @author: guoqingming
 * @create: 2019-01-04 11:57
 **/
@RestController
public class LoginController {

    @PostMapping("/login")
    public ShiroUser login(String username, String password,String remember) {


        Subject currentUser = ShiroKit.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password.toCharArray());

        if ("on".equals(remember)) {
            token.setRememberMe(true);
        } else {
            token.setRememberMe(false);
        }

        currentUser.login(token);

        ShiroUser shiroUser = ShiroKit.getUser();
        WebUtils.getSession().setAttribute("shiroUser", shiroUser);
        WebUtils.getSession().setAttribute("username", shiroUser.getUserName());
        ShiroKit.getSession().setAttribute("sessionFlag", true);
        return shiroUser;
    }

    @GetMapping("/logout")
    public void logout() {
        ShiroKit.getSubject().logout();
    }
}
