package com.secbro.drools.mapper;

import com.secbro.drools.domain.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper {
    int deleteById(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role getById(Integer id);

    int updateByIdSelective(Role record);

    int updateById(Role record);

    List<Role> listByUserId(Integer userId);

    List<Role> listRecords(String roleName);


}