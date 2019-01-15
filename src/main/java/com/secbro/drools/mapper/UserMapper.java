package com.secbro.drools.mapper;

import com.secbro.drools.domain.Menu;
import com.secbro.drools.domain.User;
import com.secbro.drools.model.UserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    int deleteById(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User getById(Integer id);

    int updateByIdSelective(User record);

    int updateById(User record);

    User getByAccount(String username);

    List<User> listRecords(@Param("username") String username, @Param("email") String email);

}