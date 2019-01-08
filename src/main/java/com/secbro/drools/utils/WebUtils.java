package com.secbro.drools.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @program: rule-web
 * @description:
 * @author: guoqingming
 * @create: 2019-01-04 15:51
 **/
public class WebUtils {

    public static HttpSession getSession() {
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        return request.getSession();
    }
}
