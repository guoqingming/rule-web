package com.secbro.drools.model;

import com.secbro.drools.domain.Project;
import lombok.Data;

/**
 * @program: rule-web
 * @description:
 * @author: guoqingming
 * @create: 2019-01-03 16:05
 **/
@Data
public class ProjectVo extends Project {

    private String companyName;
}
