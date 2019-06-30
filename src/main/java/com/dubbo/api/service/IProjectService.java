package com.dubbo.api.service;

import com.dubbo.api.vo.Project;

import java.util.List;

public interface IProjectService {

    int addProjectService(Project project);

    int deleteProjectService(Integer id);

    Project getProjectByIdService(Integer projectId);

    List<Project> getProjectListService();

    List<Project> filterUserProjectListService(Integer userId);

    List<Project> filtertypeProjectListService(Integer typeId);

    List<Project> filterEnvProjectListService(Integer envId);

    List<Project> filterUserAndEnvProjectListService(Integer userId,Integer envId);

    List<Project> filterUserAndTypeProjectListService(Integer userId,Integer typeId);

    List<Project> filterTypeAndEnvProjectListService(Integer typeId,Integer envId);

    List<Project> filterProjectListService(Integer typeId,Integer envId,Integer userId);

    List<Project> searchProject(String search);

    int updateProjectService(Project project);

}
