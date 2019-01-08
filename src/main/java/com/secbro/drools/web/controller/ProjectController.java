package com.secbro.drools.web.controller;

import com.github.pagehelper.PageInfo;
import com.secbro.drools.domain.Project;
import com.secbro.drools.model.ProjectVo;
import com.secbro.drools.service.ProjectService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: rule-web
 * @description: 项目配置
 * @author: guoqingming
 * @create: 2019-01-03 11:47
 **/
@RestController
@Api(tags = "项目维护")
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;
    
    @PostMapping("/add")
    public void add(Project project) {
        projectService.add(project);
    }

    @PostMapping("/update")
    public void update(Project project) {
        projectService.update(project);
    }

    @PostMapping("/delete")
    public void delete(Integer id) {
        projectService.delete(id);
    }

    @GetMapping("/list")
    public PageInfo<ProjectVo> listPage(Integer pageNum, Integer pageSize, Integer companyId, String projectName) {
        return projectService.listPage(pageNum, pageSize, companyId,projectName);
    }

    @GetMapping("/generateAppId")
    public String generateAppId() {
        return projectService.generateAppId();
    }
    
}
