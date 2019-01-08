package com.secbro.drools.web.controller;

import org.mvel2.MVEL;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: rule-web
 * @description:
 * @author: guoqingming
 * @create: 2019-01-02 10:31
 **/
public class Test {
    public static void main(String[] args) {
        String expression = "foobar ";

        Map vars = new HashMap();
        vars.put("foobar", new Integer(100));

        // We know this expression should return a boolean.
        Object result = MVEL.eval(expression, vars);



    }
}
