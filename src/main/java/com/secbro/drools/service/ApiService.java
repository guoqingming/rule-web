package com.secbro.drools.service;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.qm.utils.CheckUtil;
import com.secbro.drools.domain.BizStrategy;
import com.secbro.drools.domain.Project;
import com.secbro.drools.model.ApiParam;
import com.secbro.drools.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @program: rule-web
 * @description: 对外规则服务
 * @author: guoqingming
 * @create: 2019-01-04 18:16
 **/
@Service
@Slf4j
public class ApiService {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private BizStrategyService bizStrategyService;

    public Map<String, Object> executeStrategy(ApiParam param) {
        log.info("执行测试传入参数： {}", JsonUtils.toJSON(param));
        CheckUtil.isNull(param, "传入参数为空");
        CheckUtil.isBlank(param.getAppId(), "应用ID为空");
        CheckUtil.isBlank(param.getPrivateKey(), "私钥为空");
        CheckUtil.isBlank(param.getStrategyKey(), "策略为空");
        CheckUtil.isTrue(CollectionUtil.isEmpty(param.getMap()), "待验证数据为空");

        Project project = projectService.queryByAppId(param.getAppId());
        CheckUtil.isNull(project, StrUtil.format("未找到【{}】对应的项目", param.getAppId()));

        BizStrategy bizStrategy = bizStrategyService.queryByName(param.getStrategyKey());
        CheckUtil.isNull(bizStrategy, StrUtil.format("未找到【{}】对应的项目", param.getStrategyKey()));
        return null;
    }
}
