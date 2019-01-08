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
public class RoleMenu implements Serializable {
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
     * 菜单ID
     */
    @ApiModelProperty("菜单ID")
    private Integer menuId;

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
     */
    private static final long serialVersionUID = 1L;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }
}