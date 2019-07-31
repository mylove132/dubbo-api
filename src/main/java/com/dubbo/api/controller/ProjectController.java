package com.dubbo.api.controller;

import com.dubbo.api.common.bean.BaseResponse;
import com.dubbo.api.common.bean.ErrorResponse;
import com.dubbo.api.common.bean.PageInfo;
import com.dubbo.api.common.bean.SuccessResponse;
import com.dubbo.api.common.constant.CommonConstant;
import com.dubbo.api.common.constant.PermissionConstant;
import com.dubbo.api.config.AuthPermission;
import com.dubbo.api.service.IProjectService;
import com.dubbo.api.vo.Project;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@Slf4j
@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    private IProjectService projectService;



    @AuthPermission(PermissionConstant.COMMON_USER)
    @RequestMapping(value = "", method = RequestMethod.GET)
    public BaseResponse projectListController(@RequestParam(defaultValue = "1", value = "currentPage") Integer pageNum,
                                              @RequestParam(defaultValue = "10", value = "pageSize") Integer pageSize) {
        log.info("获取项目列表");
        List projects = projectService.getProjectListService(pageNum, pageSize);
        return new SuccessResponse(new PageInfo(projects));
    }

    @Transactional
    @AuthPermission(PermissionConstant.VIP)
    @RequestMapping(value = "", method = RequestMethod.POST)
    public BaseResponse addProjectController(Project project) {
        log.info("添加项目");
        if (project.getCtime() == null) {
            project.setCtime(new Date());
        }
        if (project.getUpdateTime() == null) {
            project.setUpdateTime(new Date());
        }
        int result = 0;
        try {
            result = projectService.addProjectService(project);
            log.info("添加项目：" + project.getName() + "成功");
        } catch (Exception e) {
            log.error("添加项目失败:" + e.getMessage());
            return new ErrorResponse(CommonConstant.ADD_PROJECT_FAIL);
        }
        return new SuccessResponse(result);
    }

    @Transactional
    @AuthPermission(PermissionConstant.VIP)
    @RequestMapping(value = "", method = RequestMethod.PUT)
    public BaseResponse updateProjectController(Project project) {
        log.info("更新项目");
        if (project.getUpdateTime() == null) {
            project.setUpdateTime(new Date());
        }
        int result = 0;
        try {
            result = projectService.updateProjectService(project);
            log.info("更新项目：" + project.getName() + "成功");
        } catch (Exception e) {
            log.error("更新项目失败:" + e.getMessage());
            return new ErrorResponse(CommonConstant.ADD_PROJECT_FAIL);
        }
        return new SuccessResponse(result);
    }

    @Transactional
    @AuthPermission(PermissionConstant.VIP)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public BaseResponse deleteProjectController(@PathVariable Integer id) {
        log.info("删除项目id:" + id);
        return new SuccessResponse(projectService.deleteProjectService(id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public BaseResponse getProjectController(@PathVariable Integer id) {
        log.info("根据项目id:" + id + "获取项目信息");
        return new SuccessResponse(projectService.getProjectByIdService(id));
    }

    @RequestMapping(value = "/filter", method = RequestMethod.GET)
    public BaseResponse filterProjectListController(@RequestParam(defaultValue = "0", value = "typeId") Integer typeId,
                                                    @RequestParam(defaultValue = "0", value = "envId") Integer envId,
                                                    @RequestParam(defaultValue = "0", value = "userId") Integer userId,
                                                    @RequestParam(defaultValue = "1", value = "currentPage") Integer pageNum,
                                                    @RequestParam(defaultValue = "10", value = "pageSize") Integer pageSize) {
        log.info("typeId:" + typeId + "userId:" + userId + "envId:" + envId);
        log.info("获取过滤项目列表");
        List projects = new ArrayList<>();
        if ((typeId == 0) && (envId == 0) && (userId != 0)) {
            projects = projectService.filterUserProjectListService(userId, pageNum, pageSize);
        } else if ((typeId == 0) && (envId != 0) && (userId == 0)) {
            projects = projectService.filtertypeProjectListService(envId, pageNum, pageSize);
        } else if ((typeId != 0) && (envId == 0) && (userId == 0)) {
            projects = projectService.filtertypeProjectListService(typeId, pageNum, pageSize);
        } else if ((typeId != 0) && (envId != 0) && (userId == 0)) {
            projects = projectService.filterTypeAndEnvProjectListService(typeId, envId, pageNum, pageSize);
        } else if ((typeId != 0) && (envId == 0) && (userId != 0)) {
            projects = projectService.filterUserAndTypeProjectListService(userId, typeId, pageNum, pageSize);
        } else if ((typeId == 0) && (envId != 0) && (userId != 0)) {
            projects = projectService.filterUserAndEnvProjectListService(userId, envId, pageNum, pageSize);
        } else if ((typeId == 0) && (envId == 0) && (userId == 0)) {
            projects = projectService.getProjectListService(pageNum, pageSize);
        } else {
            projects = projectService.filterProjectListService(typeId, envId, userId, pageNum, pageSize);
        }

        return new SuccessResponse(new PageInfo(projects));
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public BaseResponse searchProjectListController(String keyword,@RequestParam(defaultValue = "1", value = "currentPage") Integer pageNum,
                                                    @RequestParam(defaultValue = "10", value = "pageSize") Integer pageSize) {
        log.info("搜索项目列表");
        List projects = projectService.searchProject(keyword,pageNum,pageSize);
        return new SuccessResponse(new PageInfo(projects));
    }

    @RequestMapping(value = "/typeId/{typeId}", method = RequestMethod.GET)
    public BaseResponse getListProjectByType(@PathVariable Integer typeId,@RequestParam(defaultValue = "1", value = "currentPage") Integer pageNum,
                                             @RequestParam(defaultValue = "10", value = "pageSize") Integer pageSize) {
        List projects = projectService.filtertypeProjectListService(typeId,pageNum,pageSize);
        return new SuccessResponse(new PageInfo(projects));
    }
}
