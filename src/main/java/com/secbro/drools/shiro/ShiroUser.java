package com.secbro.drools.shiro;

import com.secbro.drools.domain.Menu;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息
 *
 * @author fengshuonan
 * @date 2016年12月5日 上午10:26:43
 */
@Data
public class ShiroUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;          // 主键ID
    private String userName;      // 账号
    private String userNameCn;         // 姓名
    private List<Integer> roleList; // 角色集
    private List<String> roleNames; // 角色名称集

    private Set<Menu> menus;


}
