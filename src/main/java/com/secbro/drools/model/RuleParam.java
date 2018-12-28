package com.secbro.drools.model;

import lombok.Data;

import java.util.List;

/**
 * @program: rule-web
 * @description: 生成规则的参数
 * @author: guoqingming
 * @create: 2018-12-27 10:41
 **/
@Data
public class RuleParam {

    private List<Expression> conditions;

    private List<Expression> outputSettings;
}
