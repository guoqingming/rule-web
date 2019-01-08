package com.secbro.drools.mapper;

import com.secbro.drools.domain.RoleMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleMenuMapper {
    int deleteById(Integer id);

    int insert(RoleMenu record);

    int insertSelective(RoleMenu record);

    RoleMenu getById(Integer id);

    int updateByIdSelective(RoleMenu record);

    int updateById(RoleMenu record);

    int deleteRelation(@Param("roleId") Integer roleId, @Param("menuId") Integer menuId);

    List<Integer> queryMenuIds(Integer roleId);
}