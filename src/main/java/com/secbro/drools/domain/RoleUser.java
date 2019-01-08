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
public class RoleUser implements Serializable {
    /**
     */
    @ApiModelProperty("")
    private Integer id;

    /**
     * 角色ID
     */
    @ApiModelProperty("角色ID")
    private Integer roleId;

    /**
     * 用户ID
     */
    @ApiModelProperty("用户ID")
    private Integer userId;

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

}