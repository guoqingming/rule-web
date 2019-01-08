package com.secbro.drools.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qm.utils.CheckUtil;
import com.secbro.drools.domain.Project;
import com.secbro.drools.mapper.ProjectMapper;
import com.secbro.drools.model.ProjectVo;
import com.secbro.drools.utils.UUIDs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: rule-web
 * @description: 项目维护
 * @author: guoqingming
 * @create: 2019-01-03 11:24
 **/
@Service
@Slf4j
public class ProjectService {

    @Resource
    private ProjectMapper projectMapper;


    public void add(Project project) {
        projectMapper.insertSelective(project);
    }

    public void update(Project project) {
        projectMapper.updateByIdSelective(project);
    }

    public void delete(Integer id) {
        CheckUtil.isNull(id,"传入ID为空");
        projectMapper.deleteById(id);
    }

    public PageInfo<ProjectVo> listPage(Integer pageNum, Integer pageSize, Integer companyId, String name) {
        if(pageNum == null){
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        PageHelper.startPage(pageNum, pageSize);
        List<ProjectVo> projects = projectMapper.listRecords(name,companyId);
        return new PageInfo<>(projects);
    }

    public String generateAppId() {
        String appId = UUIDs.getShortUuid();
        Project project = projectMapper.queryByPrivateKey(appId);
        while (project != null) {
            appId = UUIDs.getShortUuid();
            project = projectMapper.queryByPrivateKey(appId);
            if (project == null) {
                break;
            }
        }
        return appId;
    }

    public Project queryByAppId(String appId) {
        return projectMapper.queryByPrivateKey(appId);
    }
}
