package com.secbro.drools.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.secbro.drools.domain.Menu;
import com.secbro.drools.mapper.MenuMapper;
import org.apache.zookeeper.data.Id;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * @program: rule-web
 * @description: 菜单维护
 * @author: guoqingming
 * @create: 2019-01-06 21:37
 **/
@Service
public class MenuService {

    @Resource
    private MenuMapper menuMapper;

    public void add(Menu menu) {
        menuMapper.insertSelective(menu);
    }

    public void update(Menu menu) {
        menuMapper.updateByIdSelective(menu);
    }

    public void delete(Integer id) {
        menuMapper.deleteById(id);
    }

    public PageInfo<Menu> listPage(String menuName, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Menu> menus = menuMapper.listRecords(menuName);
        return new PageInfo<>(menus);
    }

    public List<Menu> list() {
        return menuMapper.listRecords(null);
    }

    public Set<Menu> authorizedMenus(String userName) {
        return menuMapper.authorizedMenus(userName);
    }
}
