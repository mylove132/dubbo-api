package com.dubbo.api.service.impl;

import com.dubbo.api.dao.ProjectMapper;
import com.dubbo.api.service.IProjectService;
import com.dubbo.api.vo.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProjectService implements IProjectService {

    @Autowired
    private ProjectMapper projectMapper;

    @Transactional
    @Override
    public int addProjectService(Project project) {
        return projectMapper.insert(project);
    }

    @Transactional
    @Override
    public int deleteProjectService(Integer id) {
        return projectMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Project getProjectByIdService(Integer projectId) {
        return projectMapper.getProjectById(projectId);
    }

    @Override
    public List<Project> getProjectListService() {
        return projectMapper.projectList();
    }

    @Override
    public List<Project> filterUserProjectListService(Integer userId) {
        return projectMapper.filterUserProjectList(userId);
    }

    @Override
    public List<Project> filtertypeProjectListService(Integer typeId) {
        return projectMapper.filterTypeProjectList(typeId);
    }

    @Override
    public List<Project> filterEnvProjectListService(Integer envId) {
        return projectMapper.filterEnvProjectList(envId);
    }

    @Override
    public List<Project> filterUserAndEnvProjectListService(Integer userId, Integer envId) {
        return projectMapper.filterUserAndEnvProjectList(userId, envId);
    }

    @Override
    public List<Project> filterUserAndTypeProjectListService(Integer userId, Integer typeId) {
        return projectMapper.filterUserAndTypeProjectList(userId, typeId);
    }

    @Override
    public List<Project> filterTypeAndEnvProjectListService(Integer typeId, Integer envId) {
        return projectMapper.filterEnvAndTypeProjectList(envId, typeId);
    }

    @Override
    public List<Project> filterProjectListService(Integer typeId, Integer envId, Integer userId) {
        return projectMapper.filterProjectList(typeId,envId,userId);
    }

    @Override
    public List<Project> searchProject(String search) {
        return projectMapper.searchProjectList(search);
    }

    @Transactional
    @Override
    public int updateProjectService(Project project) {
        return projectMapper.updateByPrimaryKeySelective(project);
    }
}
