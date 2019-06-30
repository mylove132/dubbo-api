package com.dubbo.api.dao;

import com.dubbo.api.vo.Project;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProjectMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Project record);

    int insertSelective(Project record);

    Project selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Project record);

    int updateByPrimaryKey(Project record);

    List<Project> projectList();

    List<Project> searchProjectList(@Param("search") String search);

    List<Project> filterUserProjectList(@Param("userId") Integer userId);

    List<Project> filterTypeProjectList(@Param("typeId") Integer typeId);

    List<Project> filterEnvProjectList(@Param("envId") Integer envId);

    List<Project> filterUserAndEnvProjectList(@Param("userId") Integer userId, @Param("envId") Integer envId);

    List<Project> filterUserAndTypeProjectList(@Param("userId") Integer userId, @Param("typeId") Integer typeId);

    List<Project> filterEnvAndTypeProjectList(@Param("envId") Integer envId, @Param("typeId") Integer typeId);

    List<Project> filterProjectList(@Param("typeId") Integer typeId,@Param("envId") Integer envId,@Param("userId") Integer userId);
}