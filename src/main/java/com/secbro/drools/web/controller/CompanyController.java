package com.secbro.drools.web.controller;

import com.github.pagehelper.PageInfo;
import com.secbro.drools.domain.Company;
import com.secbro.drools.service.CompanyService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: rule-web
 * @description: 公司配置
 * @author: guoqingming
 * @create: 2019-01-03 11:47
 **/
@RestController
@Api(tags = "公司维护")
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @PostMapping("/add")
    public void add(Company company) {
        companyService.add(company);
    }

    @PostMapping("/update")
    public void update(Company company) {
        companyService.update(company);
    }

    @PostMapping("/delete")
    public void delete(Integer id) {
        companyService.delete(id);
    }

    @GetMapping("/list/page")
    public PageInfo<Company> listPage(Integer pageNum, Integer pageSize, String companyName) {
        return companyService.listPage(pageNum, pageSize, companyName);
    }

    @GetMapping("/list")
    public List<Company> list() {
        return companyService.list();
    }
}
