package com.secbro.drools.web.controller;

import com.secbro.drools.model.ApiParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @program: rule-web
 * @description: 对外规则验证接口
 * @author: guoqingming
 * @create: 2019-01-04 18:05
 **/
@RestController
@RequestMapping("/api")
public class ApiController {

    public Map<String,Object> execute(ApiParam param){

        return  null;
    }
}
