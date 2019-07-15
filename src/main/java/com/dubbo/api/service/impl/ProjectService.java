package com.dubbo.api.service.impl;

import com.dubbo.api.dao.ProjectMapper;
import com.dubbo.api.service.IProjectService;
import com.dubbo.api.vo.Project;
import com.github.pagehelper.PageHelper;
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
    public List getProjectListService(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return projectMapper.projectList();
    }

    @Override
    public List<Project> filterUserProjectListService(Integer userId,Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return projectMapper.filterUserProjectList(userId);
    }

    @Override
    public List filtertypeProjectListService(Integer typeId,Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return projectMapper.filterTypeProjectList(typeId);
    }

    @Override
    public List<Project> filterEnvProjectListService(Integer envId,Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return projectMapper.filterEnvProjectList(envId);
    }

    @Override
    public List filterUserAndEnvProjectListService(Integer userId, Integer envId,Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return projectMapper.filterUserAndEnvProjectList(userId, envId);
    }

    @Override
    public List filterUserAndTypeProjectListService(Integer userId, Integer typeId,Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return projectMapper.filterUserAndTypeProjectList(userId, typeId);
    }

    @Override
    public List filterTypeAndEnvProjectListService(Integer typeId, Integer envId,Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return projectMapper.filterEnvAndTypeProjectList(envId, typeId);
    }

    @Override
    public List filterProjectListService(Integer typeId, Integer envId, Integer userId,Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return projectMapper.filterPorject(typeId,envId,userId);
    }

    @Override
    public List searchProject(String search,Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return projectMapper.searchProjectList(search);
    }

    @Transactional
    @Override
    public int updateProjectService(Project project) {
        return projectMapper.updateByPrimaryKeySelective(project);
    }
}
