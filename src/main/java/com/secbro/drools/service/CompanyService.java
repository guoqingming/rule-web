package com.secbro.drools.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qm.utils.CheckUtil;
import com.secbro.drools.domain.Company;
import com.secbro.drools.mapper.CompanyMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: rule-web
 * @description: 公司维护
 * @author: guoqingming
 * @create: 2019-01-03 11:19
 **/
@Service
@Slf4j
public class CompanyService {

    @Resource
    private CompanyMapper companyMapper;

    public void add(Company company) {
        CheckUtil.isNull(company,"传入参数为空");
        CheckUtil.isBlank(company.getName(),"公司名称为空");
        Company c = companyMapper.getByName(company.getName());
        CheckUtil.isTrue(c != null,"公司名称已存在");
        companyMapper.insertSelective(company);
    }

    public void update(Company company) {
        companyMapper.updateByIdSelective(company);
    }

    public void delete(Integer id) {
        companyMapper.deleteById(id);
    }

    /**
     * 分页查询记录
     * @param pageNum 当前页
     * @param pageSize 页大小
     * @param companyName 公司名称
     * @return
     */
    public PageInfo<Company> listPage(Integer pageNum, Integer pageSize, String companyName) {
        PageHelper.startPage(pageNum, pageSize);
        List<Company> companies = companyMapper.listByName(companyName);
        return new PageInfo<>(companies);
    }

    public List<Company> list() {
        List<Company> companies = companyMapper.listByName(null);
        return companies;
    }
}
