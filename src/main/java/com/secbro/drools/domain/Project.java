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
public class Project implements Serializable {
    /**
     */
    @ApiModelProperty("")
    private Integer id;

    /**
     * 公司ID
     */
    @ApiModelProperty("公司ID")
    private Integer companyId;

    /**
     * 项目名
     */
    @ApiModelProperty("项目名")
    private String name;

    /**
     * 描述
     */
    @ApiModelProperty("描述")
    private String remark;

    /**
     * 私钥key
     */
    @ApiModelProperty("私钥key")
    private String privateKey;

    /**
     * 是否删除，0：未删除
     */
    @ApiModelProperty("是否删除，0：未删除")
    private Integer deleteFlag;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date createAt;

    /**
     */
    @ApiModelProperty("")
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
     */
    private static final long serialVersionUID = 1L;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey == null ? null : privateKey.trim();
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }
}