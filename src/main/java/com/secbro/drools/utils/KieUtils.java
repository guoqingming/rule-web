package com.secbro.drools.utils;

import lombok.extern.slf4j.Slf4j;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.internal.utils.KieHelper;

/**
 * 你的支持是我努力的最大动力！社区的建立离不开你的支持。
 * 此系列课程正在持续更新中，相关讨论QQ（593177274）已经建立，欢迎大家加入讨论。
 * 本人博客地址：http://blog.csdn.net/wo541075754
 */
@Slf4j
public class KieUtils {

    private static KieContainer kieContainer;

    public static KieContainer getKieContainer() {
        return kieContainer;
    }

    public static void setKieContainer(KieContainer kieContainer) {

        KieUtils.kieContainer = kieContainer;
    }

    /**
     * 验证规则
     * @param ruleContent
     * @return
     */
    public static boolean verifyRule(String ruleContent) {
        KieHelper kieHelper = new KieHelper();
        kieHelper.addContent(ruleContent,ResourceType.DRL);

        Results results = kieHelper.verify();
        if (results.hasMessages(Message.Level.ERROR)) {

            log.error("规则验证失败，详情： {}",results.getMessages(Message.Level.ERROR));
            return false;
        }

        KieContainer kieContainer = kieHelper.getKieContainer();
        KieUtils.setKieContainer(kieContainer);
        return true;
    }
}
