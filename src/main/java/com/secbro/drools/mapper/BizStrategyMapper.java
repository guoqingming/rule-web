package com.secbro.drools.mapper;

import com.secbro.drools.domain.BizStrategy;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BizStrategyMapper {
    int deleteById(Integer id);

    int insert(BizStrategy record);

    int insertSelective(BizStrategy record);

    BizStrategy getById(Integer id);

    int updateByIdSelective(BizStrategy record);

    int updateByIdWithBLOBs(BizStrategy record);

    int updateById(BizStrategy record);

    BizStrategy queryByName(String name);

    List<BizStrategy> fuzzySearchStrategy(@Param("keyword") String keyword);




}