package com.secbro.drools.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @program: rule-web
 * @description: 处理页面请求
 * @author: guoqingming
 * @create: 2018-12-25 11:02
 **/
@Controller
public class ViewController {

    @GetMapping("/rule")
    public String rule() {
        return "strategy";
    }

    @GetMapping(value = {"/index","/"})
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/company")
    public String company() {
        return "company";
    }

    @GetMapping("/project")
    public String project() {
        return "project";
    }

    @GetMapping("/user")
    public String user() {
        return "user";
    }

    @GetMapping("/role")
    public String role() {
        return "role";
    }

    @GetMapping("/menu")
    public String menu() {
        return "menu";
    }
    @GetMapping("/strategy")
    public String strategy() {
        return "strategy";
    }
}
