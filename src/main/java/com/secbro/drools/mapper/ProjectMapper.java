package com.secbro.drools.mapper;

import com.secbro.drools.domain.Project;
import com.secbro.drools.model.ProjectVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProjectMapper {

    int deleteById(Integer id);

    int insert(Project record);

    int insertSelective(Project record);

    Project getById(Integer id);

    int updateByIdSelective(Project record);

    int updateById(Project record);

    List<ProjectVo> listRecords(@Param("name") String name, @Param("companyId") Integer companyId);

    Project queryByPrivateKey(String privateKey);
}