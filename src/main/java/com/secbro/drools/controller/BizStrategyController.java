package com.secbro.drools.controller;

import com.github.pagehelper.PageInfo;
import com.qm.utils.CheckUtil;
import com.secbro.drools.domain.BizRule;
import com.secbro.drools.domain.BizStrategy;
import com.secbro.drools.domain.RuleInput;
import com.secbro.drools.domain.RuleOutput;
import com.secbro.drools.model.Expression;
import com.secbro.drools.model.RuleParam;
import com.secbro.drools.service.BizRuleService;
import com.secbro.drools.service.BizStrategyService;
import com.secbro.drools.service.RuleInputService;
import com.secbro.drools.service.RuleOutputService;
import com.secbro.drools.utils.JsonUtils;
import com.secbro.drools.utils.KieUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.drools.core.base.RuleNameStartsWithAgendaFilter;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: rule-web
 * @description:
 * @author: guoqingming
 * @create: 2018-12-21 23:23
 **/
@RestController
@RequestMapping("/strategy")
@Api(tags = "策略管理")
public class BizStrategyController {

    @Autowired
    private BizRuleService bizRuleService;

    @Autowired
    private RuleInputService ruleInputService;

    @Autowired
    private RuleOutputService ruleOutputService;

    @Autowired
    private BizStrategyService bizStrategyService;

    @PostMapping("/add")
    @ApiOperation("添加策略")
    public void addRule(@RequestBody BizStrategy bizStrategy) {
        bizStrategyService.add(bizStrategy);
    }

    @PostMapping("/update")
    public void updateRule(BizStrategy bizStrategy) {
        bizStrategyService.update(bizStrategy);
    }

    @PostMapping("/delete")
    public void delete(Integer id){
        bizStrategyService.deleteStrategy(id);
    }

    @GetMapping("/get")
    public BizStrategy getById(Integer id) {
        return bizStrategyService.getById(id);
    }

    @PostMapping("/rule/test")
    public Map<String,Object> testRules(String data,Integer strategyId){
        return bizStrategyService.testRules(data, strategyId);
    }
    @PostMapping("/createRules")
    public String createRules(Integer strategyId,String data) {
        List<RuleParam> paramList = JsonUtils.toList(data, RuleParam.class);
        return bizStrategyService.createRules(strategyId, paramList);
    }
    //    @PostMapping("/extract")
//    public String extractRule(Integer ruleId,String ruleName,String condition,  String outputExpression) {
//        return bizRuleService.extractRule(ruleId,ruleName,condition,Arrays.asList(outputExpression.split(",")));
//    }
    @GetMapping("list/page")
    public PageInfo<BizStrategy> strategyPageInfo(Integer pageNum,Integer pageSize,String keyword) {
        return bizStrategyService.fuzzyQueryStrategy(pageNum, pageSize, keyword);
    }

    @PostMapping("/input/add")
    public void addRuleInput(RuleInput ruleInput) {
        ruleInputService.addRuleInput(ruleInput);
    }

    @PostMapping("/input/update")
    public void updateRuleInput(RuleInput ruleInput) {
        ruleInputService.updateRuleInput(ruleInput);
    }

    @PostMapping("/input/delete")
    public void updateRuleInput(Integer  id) {
        ruleInputService.deleteRuleInput(id);
    }

    @PostMapping("/output/add")
    public void addRuleOutput(RuleOutput ruleOutput) {
        ruleOutputService.addRuleOutput(ruleOutput);
    }

    @PostMapping("/output/update")
    public void updateRuleOutput(RuleOutput ruleOutput) {
        ruleOutputService.updateRuleOutput(ruleOutput);
    }

    @PostMapping("/output/delete")
    public void updateRuleOutput(Integer  id) {
        ruleOutputService.deleteRuleOutput(id);
    }

    @GetMapping("/input/list")
    public List<RuleInput> inputList(Integer strategyId) {
        return bizStrategyService.queryStategyInputList(strategyId);
    }

    @GetMapping("/output/list")
    public List<RuleOutput> outputList(Integer strategyId) {
        return bizStrategyService.queryStategyOutputList(strategyId);
    }
}
