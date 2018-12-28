package com.secbro.drools.mapper;

import com.secbro.drools.domain.BizRule;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BizRuleMapper {
    int deleteById(Integer id);

    int insert(BizRule record);

    int insertSelective(BizRule record);

    BizRule getById(Integer id);

    int updateByIdSelective(BizRule record);

    int updateByIdWithBLOBs(BizRule record);

    int updateById(BizRule record);

    BizRule queryByRuleName(String ruleName);
}