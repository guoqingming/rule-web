package com.secbro.drools.controller;

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
}
