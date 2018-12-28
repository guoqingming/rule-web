package com.secbro.drools.service;

import cn.hutool.core.io.FileUtil;
import com.qm.utils.CheckUtil;
import com.secbro.drools.domain.BizRule;
import com.secbro.drools.domain.RuleInput;
import com.secbro.drools.mapper.BizRuleMapper;
import com.secbro.drools.mapper.RuleInputMapper;
import com.secbro.drools.mapper.RuleOutputMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: rule-web
 * @description: 维护规则相关服务
 * @author: guoqingming
 * @create: 2018-12-21 23:04
 **/
@Service
public class BizRuleService {

    @Resource
    private BizRuleMapper ruleMapper;

    @Resource
    private RuleInputMapper  ruleInputMapper;

    @Resource
    private RuleOutputMapper ruleOutputMapper;


    public void addRule(BizRule bizRule) {
        CheckUtil.isNull(bizRule, "传入参数");
        CheckUtil.isBlank(bizRule.getRuleName(), "规则名称为空");
        BizRule rule = ruleMapper.queryByRuleName(bizRule.getRuleName());
        CheckUtil.isTrue(rule != null, "规则已存在");
        ruleMapper.insertSelective(bizRule);
    }

    public void update(BizRule bizRule) {
        ruleMapper.updateByIdSelective(bizRule);
    }

    /**
     * 生成业务规则
     *
     * @param condition        过滤条件
     * @param outputExpression 输出赋值表达式
     * @return
     */
//    public String extractRule(Integer ruleId, String ruleName, String condition, List<String> outputExpression) {
//
//        StringBuffer sb = new StringBuffer();
//        sb.append("package rules\n");
//        sb.append("import java.util.Map;\n");
//        sb.append("import java.util.HashMap;\n");
//        sb.append("rule " + ruleName +"\n");
//        sb.append("     dialect \"mvel\"\n");
//        sb.append("     when \n");
//        sb.append("         resultMap : HashMap() \n");
//        List<RuleInput> ruleInputs = ruleInputMapper.queryByRuleId(ruleId);
//
//        List<String> paramNames = ruleInputs.stream().map(ri ->
//                ri.getName()
//        ).collect(Collectors.toList());
//
//        for (String s : paramNames) {
//            String regex = "\\$\\{" + s + "\\}";
//            String repacement = "this[\""+s+"\"]";
//            condition = condition.replaceAll(regex, repacement);
//        }
//        sb.append("         map: Map("+condition+")\n");
//        sb.append("     then\n");
//        // value=${age}*2-1
//        for (String e : outputExpression) {
//            String left = e.split("=")[0];
//            String right = e.split("=")[1];
//            for (String paramName : paramNames) {
//                String regex = "\\$\\{" + paramName + "\\}";
//                String repacement = "map.get(\""+paramName+"\")";
//                right = right.replaceAll(regex, repacement);
//            }
//            sb.append("     resultMap.put(\""+left +"\"," +right+");\n");
//        }
//        sb.append("end");
//        FileUtil.writeBytes(sb.toString().getBytes(), new File("test.drl"));
//        return sb.toString();
//    }
}
