package com.secbro.drools.component;

import com.qm.utils.CheckUtil;
import com.secbro.drools.utils.JsonUtils;
import com.secbro.drools.utils.KieUtils;
import lombok.extern.slf4j.Slf4j;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.io.Resource;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.utils.KieHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.StringReader;
import java.io.UnsupportedEncodingException;

/**
 * Created by neo on 17/7/31.
 */

@Component
@Slf4j
public class DroolsRulesService {

    public void reload() throws UnsupportedEncodingException {
        KieServices kieServices = getKieServices();
        KieFileSystem kfs = kieServices.newKieFileSystem();
        kfs.write("src/main/resources/rules/111.drl", loadRules());
        KieBuilder kieBuilder = kieServices.newKieBuilder(kfs).buildAll();
        Results results = kieBuilder.getResults();
        if (results.hasMessages(Message.Level.ERROR)) {
            System.out.println(results.getMessages());
            throw new IllegalStateException("### errors ###");
        }

        KieUtils.setKieContainer(kieServices.newKieContainer(kieServices.getRepository().getDefaultReleaseId()));
        System.out.println("reload新规则重载成功");
    }

    public void reload(String ruleContent)  {
        KieServices kieServices = getKieServices();
        KieFileSystem kfs = kieServices.newKieFileSystem();
        kfs.write("src/main/resources/rules/111.drl", ruleContent);
        KieBuilder kieBuilder = kieServices.newKieBuilder(kfs).buildAll();
        Results results = kieBuilder.getResults();
        CheckUtil.isTrue(results.hasMessages(Message.Level.ERROR), "规则解析错误");

        if (results.hasMessages(Message.Level.ERROR)) {
            log.error("详细信息： {}", JsonUtils.toJSON(results.getMessages(Message.Level.ERROR)));
        }

        KieUtils.setKieContainer(kieServices.newKieContainer(kieServices.getRepository().getDefaultReleaseId()));
        log.info("reload新规则重载成功");
    }

    private String loadRules() {
        // 从数据库加载的规则
//        return "package rules\n" +
//                "\n" +
//                "import java.util.Map;\n" +
//                "import java.util.HashMap;\n" +
//                "rule \"mapTest\"\n" +
//                "    dialect \"mvel\"\n" +
//                "     when\n" +
//                "       resultMap : HashMap()\n" +
//                "       map: Map(this[\"name\"] == \"Bob\")\n" +
//                "     then\n" +
//                "         resultMap.put(\"name\",map.get(\"name\"));\n" +
//                "         System.out.println(\"执行成功\");\n" +
//                "end";
        return "";

    }

    private KieServices getKieServices() {
        return KieServices.Factory.get();
    }

    public void reloadByHelper() throws UnsupportedEncodingException {

        KieHelper kieHelper = new KieHelper();
        kieHelper.addContent(loadRules(), ResourceType.DRL);

        Results results = kieHelper.verify();
        if (results.hasMessages(Message.Level.ERROR)) {
            System.out.println(results.getMessages());
            throw new IllegalStateException("### errors ###");
        }

//        KieBase kieBase = kieHelper.build();
        KieContainer kieContainer = kieHelper.getKieContainer();


        KieUtils.setKieContainer(kieContainer);
        System.out.println("新规则重载成功");
    }

}
