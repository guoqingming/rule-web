package com.secbro.drools.model;

import lombok.Data;

/**
 * @program: rule-web
 * @description: 表达式
 * @author: guoqingming
 * @create: 2018-12-22 17:04
 **/
@Data
public class Expression {

    private Integer group;

    private String groupFlag;

    private Left left;

    private RelationOperator relationOperator;

    private Right right;

    private LogicOperator logicOperator;

}
