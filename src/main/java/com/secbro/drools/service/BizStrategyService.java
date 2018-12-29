package com.secbro.drools.service;

import cn.hutool.core.io.FileUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qm.utils.CheckUtil;
import com.secbro.drools.component.DroolsRulesService;
import com.secbro.drools.domain.BizStrategy;
import com.secbro.drools.domain.RuleInput;
import com.secbro.drools.domain.RuleOutput;
import com.secbro.drools.mapper.BizRuleMapper;
import com.secbro.drools.mapper.BizStrategyMapper;
import com.secbro.drools.mapper.RuleInputMapper;
import com.secbro.drools.mapper.RuleOutputMapper;
import com.secbro.drools.model.Expression;
import com.secbro.drools.model.RuleParam;
import com.secbro.drools.utils.JsonUtils;
import com.secbro.drools.utils.KieUtils;
import org.apache.commons.lang3.StringUtils;
import org.drools.core.base.RuleNameStartsWithAgendaFilter;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: rule-web
 * @description: 维护规则相关服务
 * @author: guoqingming
 * @create: 2018-12-21 23:04
 **/
@Service
public class BizStrategyService {

    @Resource
    private BizRuleMapper ruleMapper;

    @Resource
    private RuleInputMapper  ruleInputMapper;

    @Resource
    private RuleOutputMapper ruleOutputMapper;

    @Resource
    private BizStrategyMapper strategyMapper;

    @Autowired
    private DroolsRulesService droolsRulesService;


    public void add(BizStrategy strategy) {
        CheckUtil.isNull(strategy, "传入参数");
        CheckUtil.isBlank(strategy.getStrategyName(), "规则名称为空");
        BizStrategy bizStrategy = strategyMapper.queryByName(strategy.getStrategyName());
        CheckUtil.isTrue(bizStrategy != null, "规则已存在");
        strategyMapper.insertSelective(strategy);
    }

    public void update(BizStrategy strategy) {
        strategyMapper.updateByIdSelective(strategy);
    }


    public void deleteStrategy(Integer id) {
        CheckUtil.isNull(id,"策略ID为空");
        strategyMapper.deleteById(id);
    }

    public BizStrategy getById(Integer id) {
        return strategyMapper.getById(id);
    }
    /**
     * 模糊查询策略
     * @param pageNum 当前页
     * @param pageSize 页面大小
     * @param keyword 关键字
     * @return
     */
    public PageInfo<BizStrategy> fuzzyQueryStrategy(Integer pageNum, Integer pageSize, String keyword) {
        PageHelper.startPage(pageNum, pageSize);
        List<BizStrategy> bizStrategies = strategyMapper.fuzzySearchStrategy(keyword);
        return new PageInfo<>(bizStrategies);
    }
    public List<RuleInput> queryStategyInputList(Integer strategyId) {
        return ruleInputMapper.queryByStrategyId(strategyId);
    }


    public List<RuleOutput> queryStategyOutputList(Integer strategyId) {
        return ruleOutputMapper.queryByStategyId(strategyId);
    }

    /**
     * 生成规则
     * @param strategyId 策略ID
     * @param params
     * @return
     */
    public String createRules(Integer strategyId, List<RuleParam> params) {

        BizStrategy bizStrategy = strategyMapper.getById(strategyId);
        CheckUtil.isNull(bizStrategy,"未找到【{"+bizStrategy+"}】对应的策略");
        StringBuffer sb = new StringBuffer();
        sb.append("package rules\n");
        sb.append("import java.util.Map;\n");
        sb.append("import java.util.HashMap;\n");
        for (int i = 0; i < params.size(); i++) {
            sb.append("//" + params.get(i).getRuleDesc() + "\n");
            sb.append("rule " + bizStrategy.getStrategyName() + i +"\n");
            sb.append("\tdialect \"mvel\"\n");
            sb.append("\twhen \n");
            sb.append("\t\tresultMap : HashMap() \n");
            String str = "\t\tmap: Map(";
            for (Expression expression : params.get(i).getConditions()) {
//                map: Map(this["score"]>=60)
                   str +="this[\"";
                str += expression.getLeft().getParamName()+ "\"]" + expression.getRelationOperator().getValue();
                if("String".equals(expression.getLeft().getParamType())){
                    str += "\"" + expression.getRight().getValue() + "\"";
                  }else {
                    str += expression.getRight().getValue();
                }

                if(expression.getLogicOperator()!= null && StringUtils.isNotBlank(expression.getLogicOperator().getValue())){
                    str += expression.getLogicOperator().getValue();
                }else {
                    str += ")\n";
                }

            }
            sb.append(str);
            sb.append("\tthen\n");
            String oStr = "";
            for (Expression expression : params.get(i).getOutputSettings()) {
                oStr += "\t\tresultMap.put(";
                oStr += "\"" + expression.getLeft().getParamName() + "\",";
                if(expression.getRight().getType() == 1){
                    if("String".equals(expression.getLeft().getParamType())){
                        oStr += "\"" + expression.getRight().getValue() + "\")\n";
                    }else {
                        oStr += expression.getRight().getValue() + ")\n";
                    }
                }
            }

            sb.append(oStr);
            sb.append("end\n");
        }

        droolsRulesService.reload(sb.toString());
        FileUtil.writeBytes(sb.toString().getBytes(), new File("test.drl"));
        BizStrategy strategy = new BizStrategy();
        strategy.setId(strategyId);
        strategy.setRuleContent(sb.toString());
        strategy.setRuleParams(JsonUtils.toJSON(params));
        strategyMapper.updateByIdSelective(strategy);
        return sb.toString();
    }


    public Map<String,Object> testRules(String data, Integer strategyId){
        BizStrategy strategy = strategyMapper.getById(strategyId);
        CheckUtil.isNull(strategy,"未找到【"+strategyId+"】对应的策略");
        droolsRulesService.reload(strategy.getRuleContent());
        Map<String,Object> map = JsonUtils.toBean(data,Map.class);
        KieSession kieSession = KieUtils.getKieContainer().newKieSession();
        kieSession.insert(map);
        HashMap<String, Object> result = new HashMap<>();
        kieSession.insert(result);
        kieSession.fireAllRules(new RuleNameStartsWithAgendaFilter(strategy.getStrategyName()));
        kieSession.dispose();
        return result;
    }
}
