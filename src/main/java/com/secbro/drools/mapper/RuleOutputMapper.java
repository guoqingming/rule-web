package com.secbro.drools.mapper;

import com.secbro.drools.domain.RuleOutput;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RuleOutputMapper {
    int deleteById(Integer id);

    int insert(RuleOutput record);

    int insertSelective(RuleOutput record);

    RuleOutput getById(Integer id);

    int updateByIdSelective(RuleOutput record);

    int updateById(RuleOutput record);

    List<RuleOutput> queryByStategyId(Integer ruleId);
}