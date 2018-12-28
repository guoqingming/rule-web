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
public class BizStrategy implements Serializable {
    /**
     */
    @ApiModelProperty("")
    private Integer id;

    /**
     * 策略名称
     */
    @ApiModelProperty("策略名称")
    private String strategyName;

    /**
     * 是否启用，1：启用，0：禁用
     */
    @ApiModelProperty("是否启用，1：启用，0：禁用")
    private Integer enable;

    /**
     * 策略描述
     */
    @ApiModelProperty("策略描述")
    private String strategyDesc;

    @ApiModelProperty("规则参数集合")
    private String ruleParams;
    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date createAt;

    /**
     * 创建人
     */
    @ApiModelProperty("创建人")
    private String createBy;

    /**
     * 更新时间
     */
    @ApiModelProperty("更新时间")
    private Date updateAt;

    /**
     * 更新人
     */
    @ApiModelProperty("更新人")
    private String updateBy;

    /**
     * 规则内容
     */
    @ApiModelProperty("规则内容")
    private String ruleContent;

    /**
     */
    private static final long serialVersionUID = 1L;

}