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
public class User implements Serializable {
    /**
     */
    @ApiModelProperty("")
    private Integer id;

    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    private String userName;

    /**
     * 中文名
     */
    @ApiModelProperty("中文名")
    private String userNameCn;

    /**
     * 密码
     */
    @ApiModelProperty("密码")
    private String password;

    /**
     * 公司ID
     */
    @ApiModelProperty("公司ID")
    private Integer companyId;

    /**
     * 公司名
     */
    @ApiModelProperty("公司名")
    private String companyName;

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
     * 状态，0：冻结，1：正常
     */
    @ApiModelProperty("状态，0：冻结，1：正常")
    private Integer status;

    /**
     * md5加盐
     */
    @ApiModelProperty("md5加盐")
    private String salt;

    /**
     * 邮箱
     */
    @ApiModelProperty("邮箱")
    private String email;

    /**
     */
    private static final long serialVersionUID = 1L;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public void setUserNameCn(String userNameCn) {
        this.userNameCn = userNameCn == null ? null : userNameCn.trim();
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
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

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }
}