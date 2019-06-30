package com.dubbo.api.dao;

import com.dubbo.api.vo.ProjectType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProjectTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProjectType record);

    int insertSelective(ProjectType record);

    ProjectType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProjectType record);

    int updateByPrimaryKey(ProjectType record);

    List<ProjectType> projectTypeList();
}