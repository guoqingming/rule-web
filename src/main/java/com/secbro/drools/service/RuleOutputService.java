package com.secbro.drools.service;

import com.secbro.drools.domain.RuleOutput;
import com.secbro.drools.mapper.RuleOutputMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: rule-web
 * @description:
 * @author: guoqingming
 * @create: 2018-12-21 23:02
 **/
@Service
@Slf4j
public class RuleOutputService {


    @Resource
    private RuleOutputMapper ruleOutputMapper;

    public void addRuleOutput(RuleOutput ruleOutput) {
        ruleOutputMapper.insertSelective(ruleOutput);
    }

    public void updateRuleOutput(RuleOutput ruleOutput) {
        ruleOutputMapper.updateByIdSelective(ruleOutput);
    }

    public void deleteRuleOutput(Integer id) {
        ruleOutputMapper.deleteById(id);
    }

    public List<RuleOutput> ruleOutputList(Integer strategyId) {
        return ruleOutputMapper.queryByStategyId(strategyId);
    }
}
