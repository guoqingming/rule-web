package com.secbro.drools.model;

import com.secbro.drools.domain.User;
import lombok.Data;

/**
 * @program: rule-web
 * @description:
 * @author: guoqingming
 * @create: 2019-01-04 22:37
 **/
@Data
public class UserVo extends User {

    private String companyName;
}
