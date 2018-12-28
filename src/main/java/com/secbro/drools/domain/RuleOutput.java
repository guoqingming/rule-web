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
public class RuleOutput implements Serializable {
    /**
     */
    @ApiModelProperty("")
    private Integer id;

    /**
     * 参数名
     */
    @ApiModelProperty("参数名")
    private String name;

    /**
     * 描述
     */
    @ApiModelProperty("描述")
    private String desc;

    /**
     * 规则ID
     */
    @ApiModelProperty("规则ID")
    private Integer strategyId;

    /**
     * 参数类型
     */
    @ApiModelProperty("参数类型")
    private String type;

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
     */
    private static final long serialVersionUID = 1L;


}