package com.dubbo.api.dao;

import com.dubbo.api.vo.Project;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProjectMapper {

    Project getProjectById(Integer id);

    int deleteByPrimaryKey(Integer id);

    int insert(Project record);

    int insertSelective(Project record);

    Project selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Project record);

    int updateByPrimaryKey(Project record);

    List projectList();

    List searchProjectList(@Param("search") String search);

    List filterUserProjectList(@Param("userId") Integer userId);

    List filterTypeProjectList(@Param("typeId") Integer typeId);

    List filterEnvProjectList(@Param("envId") Integer envId);

    List filterUserAndEnvProjectList(@Param("userId") Integer userId, @Param("envId") Integer envId);

    List filterUserAndTypeProjectList(@Param("userId") Integer userId, @Param("typeId") Integer typeId);

    List filterEnvAndTypeProjectList(@Param("envId") Integer envId, @Param("typeId") Integer typeId);

    List filterPorject(@Param("typeId") Integer typeId,@Param("envId") Integer envId,@Param("userId") Integer userId);
}