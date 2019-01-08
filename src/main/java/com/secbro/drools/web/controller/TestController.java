package com.secbro.drools.web.controller;

import com.secbro.drools.component.DroolsRulesService;
import com.secbro.drools.model.ScoreModal;
import com.secbro.drools.model.TestDataModel;
import com.secbro.drools.model.fact.AddressCheckResult;
import com.secbro.drools.model.Address;
import com.secbro.drools.utils.KieUtils;
import org.drools.core.base.RuleNameEqualsAgendaFilter;
import org.drools.core.base.RuleNameStartsWithAgendaFilter;
import org.kie.api.runtime.KieSession;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 你的支持是我努力的最大动力！社区的建立离不开你的支持。
 * 此系列课程正在持续更新中，相关讨论QQ（593177274）已经建立，欢迎大家加入讨论。
 * 本人博客地址：http://blog.csdn.net/wo541075754
 */
@RequestMapping("/test")
@RestController
public class TestController {

    @Resource
    private DroolsRulesService rules;

    @RequestMapping("/address")
    public void test(){
        KieSession kieSession = KieUtils.getKieContainer().newKieSession();

        Address address = new Address();
        address.setPostcode("994251");

        AddressCheckResult result = new AddressCheckResult();
        kieSession.insert(address);
        kieSession.insert(result);
        int ruleFiredCount = kieSession.fireAllRules();
        System.out.println("触发了" + ruleFiredCount + "条规则");

        if(result.isPostCodeResult()){
            System.out.println("规则校验通过");
        }

        kieSession.dispose();
    }

    @RequestMapping("/reload")
    public String reload() throws IOException {
        rules.reload();
        return "ok";
    }

    @PostMapping("/score")
    public ScoreModal score(TestDataModel model) {
        Map<String,String> map = new HashMap<>();
        KieSession kieSession = KieUtils.getKieContainer().newKieSession();
        kieSession.insert(model);
        ScoreModal scoreModal = new ScoreModal(0);
        kieSession.insert(scoreModal);
        int ruleFiredCount = kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("TestDrl"));
//        int ruleFiredCount = kieSession.fireAllRules();
        System.out.println("触发了" + ruleFiredCount + "条规则");
        kieSession.dispose();
        return scoreModal;
    }

    @PostMapping("/mapTest")
    public Map mapTest(String name,Integer age,double score) {

        Map<String,Object> map = new HashMap<>();
        map.put("name", name);
        map.put("age", age);
        map.put("score", score);
        KieSession kieSession = KieUtils.getKieContainer().newKieSession();
        kieSession.insert(map);
        HashMap<String, Object> result = new HashMap<>();
        kieSession.insert(result);
        int ruleFiredCount = kieSession.fireAllRules(new RuleNameStartsWithAgendaFilter("scoreCheck"));
//        int ruleFiredCount = kieSession.fireAllRules();
        System.out.println("触发了" + ruleFiredCount + "条规则");
        kieSession.dispose();
        return result;
    }
}
