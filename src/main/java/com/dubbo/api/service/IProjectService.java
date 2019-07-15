package com.dubbo.api.service;

import com.dubbo.api.vo.Project;

import java.util.List;

public interface IProjectService {

    int addProjectService(Project project);

    int deleteProjectService(Integer id);

    Project getProjectByIdService(Integer projectId);

    List getProjectListService(Integer pageNum, Integer pageSize);

    List filterUserProjectListService(Integer userId,Integer pageNum, Integer pageSize);

    List filtertypeProjectListService(Integer typeId,Integer pageNum, Integer pageSize);

    List filterEnvProjectListService(Integer envId,Integer pageNum, Integer pageSize);

    List filterUserAndEnvProjectListService(Integer userId,Integer envId,Integer pageNum, Integer pageSize);

    List filterUserAndTypeProjectListService(Integer userId,Integer typeId,Integer pageNum, Integer pageSize);

    List filterTypeAndEnvProjectListService(Integer typeId,Integer envId,Integer pageNum, Integer pageSize);

    List filterProjectListService(Integer typeId,Integer envId,Integer userId,Integer pageNum, Integer pageSize);

    List searchProject(String search,Integer pageNum, Integer pageSize);

    int updateProjectService(Project project);

}
