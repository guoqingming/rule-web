package com.secbro.drools.web.controller;

import com.github.pagehelper.PageInfo;
import com.secbro.drools.domain.Menu;
import com.secbro.drools.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: rule-web
 * @description: 菜单维护
 * @author: guoqingming
 * @create: 2019-01-06 21:37
 **/
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @PostMapping("/add")
    public void add(Menu menu) {
        menuService.add(menu);
    }

    @PostMapping("/update")
    public void update(Menu menu) {

        menuService.update(menu);
    }

    @PostMapping("/delete")
    public void delete(Integer id) {
        menuService.delete(id);
    }

    @GetMapping("/list/page")
    public PageInfo<Menu> listPage(String menuName, Integer pageNum, Integer pageSize) {
        return menuService.listPage(menuName, pageNum, pageSize);
    }

    @GetMapping("/list")
    public List<Menu> list() {
        return menuService.list();
    }
}
