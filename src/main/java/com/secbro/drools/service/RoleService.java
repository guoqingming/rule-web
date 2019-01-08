package com.secbro.drools.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qm.utils.CheckUtil;
import com.secbro.drools.domain.Role;
import com.secbro.drools.domain.RoleMenu;
import com.secbro.drools.domain.RoleUser;
import com.secbro.drools.mapper.RoleMapper;
import com.secbro.drools.mapper.RoleMenuMapper;
import com.secbro.drools.mapper.RoleUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @program: rule-web
 * @description: 角色维护
 * @author: guoqingming
 * @create: 2019-01-04 17:03
 **/
@Service
@Slf4j
public class RoleService {

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private RoleMenuMapper roleMenuMapper;

    @Resource
    private RoleUserMapper roleUserMapper;

    public void add(Role role) {
        roleMapper.insertSelective(role);
    }

    public void update(Role role) {
        roleMapper.updateByIdSelective(role);
    }

    public void delete(Integer roleId) {
        roleMapper.deleteById(roleId);
    }

    /**
     * 分页查询
     * @param roleName 角色名称
     * @param pageNum 当前页
     * @param pageSize 页面大小
     * @return
     */
    public PageInfo<Role> listPage(String roleName, Integer pageNum, Integer pageSize) {
        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 15;
        }

        PageHelper.startPage(pageNum, pageSize);
        List<Role> roles = roleMapper.listRecords(roleName);
        return new PageInfo<>(roles);

    }

    public List<Role> list() {
        return roleMapper.listRecords(null);
    }
    public void bindMenu(Integer roleId, Integer menuId) {
        CheckUtil.isNull(roleId,"角色ID为空");
        CheckUtil.isNull(menuId,"菜单ID为空");
        RoleMenu roleMenu = new RoleMenu();
        roleMenu.setMenuId(menuId);
        roleMenu.setRoleId(roleId);
        roleMenu.setCreateAt(new Date());
        roleMenuMapper.insertSelective(roleMenu);
    }


    public void unbindMenu(Integer roleId, Integer menuId) {
        CheckUtil.isNull(roleId,"角色ID为空");
        CheckUtil.isNull(menuId,"菜单ID为空");
        roleMenuMapper.deleteRelation(roleId, menuId);
    }

    public void bindUser(Integer roleId, Integer userId) {
        CheckUtil.isNull(roleId,"角色ID为空");
        CheckUtil.isNull(userId,"菜单ID为空");
        RoleUser roleUser = new RoleUser();
        roleUser.setUserId(userId);
        roleUser.setRoleId(roleId);
        roleUser.setCreateAt(new Date());
        roleUserMapper.insertSelective(roleUser);
    }


    public void unbindUser(Integer roleId, Integer userId) {
        CheckUtil.isNull(roleId,"角色ID为空");
        CheckUtil.isNull(userId,"菜单ID为空");
        roleUserMapper.deleteRelation(roleId, userId);
    }

    public List<Integer> menuIds(Integer roleId) {
        return roleMenuMapper.queryMenuIds(roleId);
    }


}
