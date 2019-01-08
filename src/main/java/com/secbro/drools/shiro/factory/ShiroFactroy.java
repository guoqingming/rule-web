package com.secbro.drools.shiro.factory;

import com.secbro.drools.domain.Role;
import com.secbro.drools.domain.User;
import com.secbro.drools.mapper.MenuMapper;
import com.secbro.drools.mapper.RoleMapper;
import com.secbro.drools.mapper.UserMapper;
import com.secbro.drools.shiro.ShiroUser;
import com.secbro.drools.utils.SpringContextHolder;
import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
@DependsOn("springContextHolder")
@Transactional(readOnly = true)
public class ShiroFactroy implements IShiro {

    @Resource
    private UserMapper userMapper;

    @Resource
    private MenuMapper menuMapper;

    @Resource
    private RoleMapper roleMapper;

    public static IShiro me() {
        return SpringContextHolder.getBean(IShiro.class);
    }

    @Override
    public User user(String account) {

        User user = userMapper.getByAccount(account);

        // 账号不存在
        if (null == user) {
            throw new CredentialsException();
        }
        // 账号被冻结
        if (user.getStatus() != 1) {
            throw new LockedAccountException();
        }
        return user;
    }

    @Override
    public ShiroUser shiroUser(User user) {
        ShiroUser shiroUser = new ShiroUser();

        shiroUser.setId(user.getId());
        shiroUser.setUserName(user.getUserName());
        shiroUser.setUserNameCn(user.getUserNameCn());

        List<Role> roles = roleMapper.listByUserId(user.getId());
        List<Integer> roleList = roles.stream().map(Role::getId).collect(Collectors.toList());
        List<String> roleNameList = roles.stream().map(Role::getName).collect(Collectors.toList());
        shiroUser.setRoleList(roleList);
        shiroUser.setRoleNames(roleNameList);

        return shiroUser;
    }

    @Override
    public List<String> findPermissionsByRoleId(Integer roleId) {
        return menuMapper.queryMenuUrlList(roleId);
    }

    @Override
    public String findRoleNameByRoleId(Integer roleId) {

        Role role = roleMapper.getById(roleId);
        if(role != null){
            return role.getName();
        }
        return null;
    }

    @Override
    public SimpleAuthenticationInfo info(ShiroUser shiroUser, User user, String realmName) {
        String credentials = user.getPassword();

        // 密码加盐处理
        String source = user.getSalt();
        ByteSource credentialsSalt = new Md5Hash(source);
        return new SimpleAuthenticationInfo(shiroUser, credentials, credentialsSalt, realmName);
    }

}
