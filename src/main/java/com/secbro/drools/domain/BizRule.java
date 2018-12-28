package com.secbro.drools.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 *  
 */
@ApiModel
@Data
public class BizRule implements Serializable {
    /**
     */
    @ApiModelProperty("")
    private Integer id;

    /**
     * 规则名称
     */
    @ApiModelProperty("规则名称")
    private String ruleName;

    /**
     * 规则描述
     */
    @ApiModelProperty("规则描述")
    private String ruleDesc;

    /**
     * 过滤条件
     */
    @ApiModelProperty("过滤条件")
    private String filterExpression;

    /**
     * 输出表达式
     */
    @ApiModelProperty("输出表达式")
    private String outputExpression;

    /**
     * 是否启动，0：未启用，1：启用
     */
    @ApiModelProperty("是否启动，0：未启用，1：启用")
    private Integer enable;

    /**
     * 创建人
     */
    @ApiModelProperty("创建人")
    private String createBy;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date createAt;

    /**
     * 更新人
     */
    @ApiModelProperty("更新人")
    private String updateBy;

    /**
     * 更新时间
     */
    @ApiModelProperty("更新时间")
    private Date updateAt;

    /**
     * 规则内容
     */
    @ApiModelProperty("规则内容")
    private String content;

    /**
     */
    private static final long serialVersionUID = 1L;
}