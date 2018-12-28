package com.secbro.drools.service;

import com.github.pagehelper.PageInfo;
import com.secbro.drools.domain.RuleInput;
import com.secbro.drools.mapper.RuleInputMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: rule-web
 * @description:
 * @author: guoqingming
 * @create: 2018-12-21 23:01
 **/
@Service
@Slf4j
public class RuleInputService {

    @Resource
    private RuleInputMapper ruleInputMapper;

    public void addRuleInput(RuleInput ruleInput) {
        ruleInputMapper.insertSelective(ruleInput);
    }

    public void updateRuleInput(RuleInput ruleInput) {
        ruleInputMapper.updateByIdSelective(ruleInput);
    }

    public void deleteRuleInput(Integer id) {
        ruleInputMapper.deleteById(id);
    }


    public List<RuleInput> ruleInputList(Integer strategyId) {

        return ruleInputMapper.queryByStrategyId(strategyId);
    }
}
