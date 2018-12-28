package com.secbro.drools.model;

import lombok.Data;

/**
 * @program: rule-web
 * @description: 表达式左边部分
 * @author: guoqingming
 * @create: 2018-12-22 17:05
 **/
@Data
public class Left {

    /**
     * 参数ID
     */
    private Integer paramId;

    /**
     * 参数名
     */
    private String paramName;

    /**
     *
     */
    private String paramDesc;

    /**
     * 参数类型
     */
    private String paramType;

}
