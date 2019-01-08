package com.secbro.drools.mapper;

import com.secbro.drools.domain.Company;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CompanyMapper {
    int deleteById(Integer id);

    int insert(Company record);

    int insertSelective(Company record);

    Company getById(Integer id);

    int updateByIdSelective(Company record);

    int updateById(Company record);

    List<Company> listByName(@Param("companyName") String companyName);

    Company getByName(String name);
}