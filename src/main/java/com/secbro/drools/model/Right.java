package com.secbro.drools.model;

import lombok.Data;

/**
 * @program: rule-web
 * @description: 表达式右边部分
 * @author: guoqingming
 * @create: 2018-12-22 17:24
 **/
@Data
public class Right {

    /**
     * 类型，1：常量，2：变量，3：自定义
     */
    private Integer type;

    private String value;


}
