package com.dubbo.api.dao;

import com.dubbo.api.vo.ProjectEnv;

import java.util.List;

public interface ProjectEnvMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProjectEnv record);

    int insertSelective(ProjectEnv record);

    ProjectEnv selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProjectEnv record);

    int updateByPrimaryKey(ProjectEnv record);

    List<ProjectEnv> listEnv();
}