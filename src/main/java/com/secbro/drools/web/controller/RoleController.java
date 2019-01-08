package com.secbro.drools.web.controller;

import com.github.pagehelper.PageInfo;
import com.secbro.drools.domain.Role;
import com.secbro.drools.service.RoleService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: rule-web
 * @description: 角色维护
 * @author: guoqingming
 * @create: 2019-01-04 17:20
 **/
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/add")
    public void add(Role role) {
        roleService.add(role);
    }

    @PostMapping("/update")
    public void update(Role role) {
        roleService.update(role);
    }

    @PostMapping("/delete")
    public void delete(Integer roleId) {
        roleService.delete(roleId);
    }

    @GetMapping("/list/page")
    public PageInfo<Role> listPage(String roleName, Integer pageNum, Integer pageSize) {
        return roleService.listPage(roleName, pageNum, pageSize);
    }

    @GetMapping("/list")
    public List<Role> list() {
        return roleService.list();
    }

    @PostMapping("/menu/bind")
    public void bindMenu(Integer roleId, Integer menuId) {
        roleService.bindMenu(roleId,menuId);
    }

    @PostMapping("/menu/unbind")
    public void unbindMenu(Integer roleId, Integer menuId) {

        roleService.unbindMenu(roleId,menuId);
    }

    @GetMapping("menuIds")
    public List<Integer> menuIds(Integer roleId) {
        return roleService.menuIds(roleId);
    }

    @PostMapping("/user/bind")
    public void bindUser(Integer roleId, Integer userId) {
        roleService.bindUser(roleId,userId);
    }

    @PostMapping("/user/unbind")
    public void unbindUser(Integer roleId, Integer userId) {
        roleService.unbindUser(roleId,userId);
    }


}
