package com.secbro.drools.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * @program: rule-web
 * @description:
 * @author: guoqingming
 * @create: 2019-01-04 18:11
 **/
@Data
public class ApiParam implements Serializable {

    private String appId;

    private String privateKey;

    private String strategyKey;


    private Map<String,Object> map;
}
