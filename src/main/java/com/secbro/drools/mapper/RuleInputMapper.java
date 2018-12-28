package com.secbro.drools.mapper;

import com.secbro.drools.domain.RuleInput;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RuleInputMapper {
    int deleteById(Integer id);

    int insert(RuleInput record);

    int insertSelective(RuleInput record);

    RuleInput getById(Integer id);

    int updateByIdSelective(RuleInput record);

    int updateById(RuleInput record);

     List<RuleInput> queryByStrategyId(Integer ruleId);
}