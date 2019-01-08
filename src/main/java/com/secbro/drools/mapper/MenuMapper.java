package com.secbro.drools.mapper;

import com.secbro.drools.domain.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface MenuMapper {
    int deleteById(Integer id);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu getById(Integer id);

    int updateByIdSelective(Menu record);

    int updateById(Menu record);

    List<String> queryMenuUrlList(Integer roleId);

    List<Menu> listRecords(@Param("menuName") String menuName);
}