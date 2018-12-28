package com.secbro.drools.model;

import lombok.Data;

/**
 * @program: rule-web
 * @description: 逻辑运算符
 * @author: guoqingming
 * @create: 2018-12-22 17:20
 **/
@Data
public class RelationOperator {

    /**
     * 运算符中文名
     */
    private String name;

    /**
     * 运算符
     */
    private String value;
}
