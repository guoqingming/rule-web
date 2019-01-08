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
public class Menu implements Serializable {
    /**
     */
    @ApiModelProperty("")
    private Integer id;

    /**
     * 菜单名称
     */
    @ApiModelProperty("菜单名称")
    private String name;

    /**
     * 菜单图标
     */
    @ApiModelProperty("菜单图标")
    private String icon;

    /**
     * 顺序
     */
    @ApiModelProperty("顺序")
    private Integer order;

    /**
     * 菜单URL
     */
    @ApiModelProperty("菜单URL")
    private String url;

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

}