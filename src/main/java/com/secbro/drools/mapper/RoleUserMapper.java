package com.secbro.drools.mapper;

import com.secbro.drools.domain.RoleUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleUserMapper {
    int deleteById(Integer id);

    int insert(RoleUser record);

    int insertSelective(RoleUser record);

    RoleUser getById(Integer id);

    int updateByIdSelective(RoleUser record);

    int updateById(RoleUser record);


    int deleteRelation(@Param("roleId") Integer roleId, @Param("userId") Integer userId);

    List<Integer> roleIds(Integer userId);


}