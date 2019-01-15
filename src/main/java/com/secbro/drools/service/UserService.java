package com.secbro.drools.service;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qm.utils.CheckUtil;
import com.secbro.drools.domain.Menu;
import com.secbro.drools.domain.User;
import com.secbro.drools.mapper.RoleUserMapper;
import com.secbro.drools.mapper.UserMapper;
import com.secbro.drools.model.UserVo;
import com.secbro.drools.shiro.ShiroKit;
import com.secbro.drools.shiro.ShiroUser;
import com.secbro.drools.utils.StrKit;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * @program: rule-web
 * @description: 用户维护
 * @author: guoqingming
 * @create: 2019-01-04 16:47
 **/
@Service
@Slf4j
public class UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private RoleUserMapper roleUserMapper;

    @Value("${user.default.password}")
    private String defaultPassword;

    public void add(User user) {
        user.setSalt(ShiroKit.getRandomSalt(5));
        user.setPassword(ShiroKit.md5(defaultPassword, user.getSalt()));
        Object principal = SecurityUtils.getSubject().getPrincipal();
        userMapper.insertSelective(user);
    }

    public void update(User user) {
        userMapper.updateByIdSelective(user);
    }

    public void delete(Integer id) {
        userMapper.deleteById(id);
    }
    public PageInfo<User> listPage(String username, String email, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> users = userMapper.listRecords(username, email);
        return new PageInfo<>(users);
    }

    public List<User> list() {
        return userMapper.listRecords(null, null);
    }
    public void resetPassword(Integer userId) {
        User user = userMapper.getById(userId);
        CheckUtil.isNull(user,StrUtil.format("未找到【{}】对应的用户",userId));
        user.setSalt(ShiroKit.getRandomSalt(5));
        user.setPassword(ShiroKit.md5(defaultPassword, user.getSalt()));
        userMapper.updateByIdSelective(user);
    }

    /**
     * 启用用户
     * @param userId 用户ID
     */
    public void enableUser(Integer userId) {
        CheckUtil.isNull(userId,"用户ID为空");
        User user = new User();
        user.setId(userId);
        user.setStatus(1);
        userMapper.updateByIdSelective(user);

    }

    /**
     * 禁用用户
     * @param userId 用户ID
     */
    public void disableUser(Integer userId) {
        CheckUtil.isNull(userId,"用户ID为空");
        User user = new User();
        user.setId(userId);
        user.setStatus(0);
        userMapper.updateByIdSelective(user);
    }

    public void changePassword(Integer userId,String newPassword){
        User user = userMapper.getById(userId);
        CheckUtil.isNull(user,"未找到相应的用户");
        ShiroUser shiroUser = ShiroKit.getUser();
        CheckUtil.isTrue(!user.getUserName().equals(shiroUser.getUserName()),"修改密码失败");
        user.setSalt(ShiroKit.getRandomSalt(5));
        user.setPassword(ShiroKit.md5(newPassword, user.getSalt()));
        userMapper.updateByIdSelective(user);

    }

    public List<Integer> roleIds(Integer userId) {

        return roleUserMapper.roleIds(userId);
    }

}
