package com.dubbo.api.controller;

import com.dubbo.api.common.bean.BaseResponse;
import com.dubbo.api.common.bean.ErrorResponse;
import com.dubbo.api.common.bean.SuccessResponse;
import com.dubbo.api.common.constant.CommonConstant;
import com.dubbo.api.common.constant.PermissionConstant;
import com.dubbo.api.common.util.DateUtil;
import com.dubbo.api.config.AuthPermission;
import com.dubbo.api.dao.ProjectEnvMapper;
import com.dubbo.api.dao.ProjectTypeMapper;
import com.dubbo.api.service.IProjectService;
import com.dubbo.api.service.IUserService;
import com.dubbo.api.vo.Project;
import com.dubbo.api.vo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Slf4j
@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    private IProjectService projectService;

    @Autowired
    private IUserService userService;

    @Autowired
    private ProjectEnvMapper projectEnvMapper;

    @Autowired
    private ProjectTypeMapper projectTypeMapper;


    @AuthPermission(PermissionConstant.COMMON_USER)
    @RequestMapping(value = "",method = RequestMethod.GET)
    public BaseResponse projectListController(){
        log.info("获取项目列表");
        List<Map> listMap = new ArrayList<>();
        List<Project> projects = projectService.getProjectListService();
        if (projects.size() > 0){
            for (Project pro:projects){
                Map<String, Object> result = new HashMap<>();
                String envName = projectEnvMapper.selectByPrimaryKey(pro.getEnv()).getName();
                String typeName = projectTypeMapper.selectByPrimaryKey(pro.getType()).getName();
                User user = userService.getUserById(pro.getUserId());
                result.put("userId",user.getId());
                result.put("username",user.getName());
                result.put("id",pro.getId());
                result.put("envName",envName);
                result.put("typeName",typeName);
                result.put("envId",pro.getEnv());
                result.put("typeId",pro.getType());
                result.put("projectName",pro.getName());
                result.put("creteTime", DateUtil.dateFormate(pro.getCtime()));
                result.put("updateTime", DateUtil.dateFormate(pro.getUpdateTime()));
                result.put("desc",pro.getDescption());
                listMap.add(result);
            }
        }
        return new SuccessResponse(listMap);
    }

    @AuthPermission(PermissionConstant.VIP)
    @RequestMapping(value = "",method = RequestMethod.POST)
    public BaseResponse addProjectController(Project project){
        log.info("添加项目");
        if (project.getCtime() == null){
            project.setCtime(new Date());
        }
        if (project.getUpdateTime() == null){
            project.setUpdateTime(new Date());
        }
        int result = 0;
        try {
            result = projectService.addProjectService(project);
            log.info("添加项目："+project.getName()+"成功");
        }catch (Exception e){
            log.error("添加项目失败:"+e.getMessage());
            return new ErrorResponse(CommonConstant.ADD_PROJECT_FAIL);
        }
        return new SuccessResponse(result);
    }

    @AuthPermission(PermissionConstant.VIP)
    @RequestMapping(value = "",method = RequestMethod.PUT)
    public BaseResponse updateProjectController(Project project){
        log.info("更新项目");
        if (project.getUpdateTime() == null){
            project.setUpdateTime(new Date());
        }
        int result = 0;
        try {
            result = projectService.updateProjectService(project);
            log.info("更新项目："+project.getName()+"成功");
        }catch (Exception e){
            log.error("更新项目失败:"+e.getMessage());
            return new ErrorResponse(CommonConstant.ADD_PROJECT_FAIL);
        }
        return new SuccessResponse(result);
    }

    @AuthPermission(PermissionConstant.VIP)
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public BaseResponse deleteProjectController(@PathVariable Integer id){
        log.info("删除项目id:"+id);
        return new SuccessResponse(projectService.deleteProjectService(id));
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public BaseResponse getProjectController(@PathVariable Integer id){
        log.info("根据项目id:"+id+"获取项目信息");
        return new SuccessResponse(projectService.getProjectByIdService(id));
    }

    @RequestMapping(value = "/filter",method = RequestMethod.GET)
    public BaseResponse filterProjectListController(Integer typeId, Integer envId, Integer userId){
        log.info("typeId:"+typeId+"userId:"+userId+"envId:"+envId);
        log.info("获取过滤项目列表");
        List<Project> projects = new ArrayList<>();
        if ((typeId == 0)&&(envId == 0)&& (userId != 0)){
            projects = projectService.filterUserProjectListService(userId);
        }else if ((typeId == 0)&&(envId != 0)&&(userId == 0)){
            projects = projectService.filtertypeProjectListService(envId);
        }else if ((typeId != 0)&&(envId == 0)&&(userId == 0)){
            projects = projectService.filtertypeProjectListService(typeId);
        }else if ((typeId != 0)&&(envId != 0 )&&( userId == 0)){
            projects = projectService.filterTypeAndEnvProjectListService(typeId, envId);
        }else if ((typeId != 0)&&(envId == 0)&&(userId != 0)){
            projects = projectService.filterUserAndTypeProjectListService(userId, typeId);
        }else if ((typeId == 0)&&(envId != 0)&&(userId != 0)){
            projects = projectService.filterUserAndEnvProjectListService(userId, envId);
        }else if ((typeId == 0)&&(envId == 0)&&(userId == 0)){
            projects = projectService.getProjectListService();
        }
        else {
            projects = projectService.filterProjectListService(typeId,envId,userId);
        }
        List<Map> listMap = new ArrayList<>();
        if (projects == null){
            return new SuccessResponse(listMap);
        }
        if (projects.size() > 0){
            for (Project pro:projects){
                Map<String, Object> result = new HashMap<>();
                String envName = projectEnvMapper.selectByPrimaryKey(pro.getEnv()).getName();
                String typeName = projectTypeMapper.selectByPrimaryKey(pro.getType()).getName();
                User user = userService.getUserById(pro.getUserId());
                result.put("userId",user.getId());
                result.put("username",user.getName());
                result.put("id",pro.getId());
                result.put("envName",envName);
                result.put("typeName",typeName);
                result.put("envId",pro.getEnv());
                result.put("typeId",pro.getType());
                result.put("projectName",pro.getName());
                result.put("creteTime", DateUtil.dateFormate(pro.getCtime()));
                result.put("updateTime", DateUtil.dateFormate(pro.getUpdateTime()));
                result.put("desc",pro.getDescption());
                listMap.add(result);
            }
        }
        return new SuccessResponse(listMap);
    }

    @RequestMapping(value = "/search",method = RequestMethod.GET)
    public BaseResponse searchProjectListController(String keyword){
        log.info("搜索项目列表");
        List<Map> listMap = new ArrayList<>();
        List<Project> projects = projectService.searchProject(keyword);
        if (projects == null){
            return new SuccessResponse(listMap);
        }
        if (projects.size() > 0){
            for (Project pro:projects){
                Map<String, Object> result = new HashMap<>();
                String envName = projectEnvMapper.selectByPrimaryKey(pro.getEnv()).getName();
                String typeName = projectTypeMapper.selectByPrimaryKey(pro.getType()).getName();
                User user = userService.getUserById(pro.getUserId());
                result.put("userId",user.getId());
                result.put("username",user.getName());
                result.put("id",pro.getId());
                result.put("envName",envName);
                result.put("typeName",typeName);
                result.put("envId",pro.getEnv());
                result.put("typeId",pro.getType());
                result.put("projectName",pro.getName());
                result.put("creteTime", DateUtil.dateFormate(pro.getCtime()));
                result.put("updateTime", DateUtil.dateFormate(pro.getUpdateTime()));
                result.put("desc",pro.getDescption());
                listMap.add(result);
            }
        }
        return new SuccessResponse(listMap);
    }

    @RequestMapping(value = "/typeId/{typeId}",method = RequestMethod.GET)
    public BaseResponse getListProjectByType(@PathVariable Integer typeId){
        return new SuccessResponse(projectService.filtertypeProjectListService(typeId));
    }
}
