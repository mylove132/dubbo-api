package com.dubbo.api.controller;

import com.alibaba.fastjson.JSONObject;
import com.dubbo.api.common.bean.BaseResponse;
import com.dubbo.api.common.bean.ErrorResponse;
import com.dubbo.api.common.bean.SuccessResponse;
import com.dubbo.api.common.constant.CommonConstant;
import com.dubbo.api.common.constant.PermissionConstant;
import com.dubbo.api.common.util.DateUtil;
import com.dubbo.api.config.AuthPermission;
import com.dubbo.api.dao.*;
import com.dubbo.api.service.IScriptService;
import com.dubbo.api.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@Slf4j
@RestController
@RequestMapping("/api/script")
public class ScriptController {

    @Autowired
    private IScriptService scriptService;
    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private RequestTypeMapper requestTypeMapper;
    @Autowired
    private ProtocolMapper protocolMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ProjectEnvMapper projectEnvMapper;

    @RequestMapping(value = "",method = RequestMethod.GET)
    public BaseResponse scriptListController(){
        List<Map> result = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        List<Script> scripts = scriptService.listScriptService();
        for (Script script:scripts){
            String json = JSONObject.toJSONString(script);
            map = JSONObject.parseObject(json, Map.class);
            if (script.getProjectId() != 0 && script.getProjectId() != null){
                Project project = projectMapper.selectByPrimaryKey(script.getProjectId());
                if (project.getEnv() != 0 && project.getEnv() != null){
                    ProjectEnv projectEnv = projectEnvMapper.selectByPrimaryKey(project.getEnv());
                    map.put("envId",projectEnv.getId());
                    map.put("envName",projectEnv.getName());
                }
                map.put("projectName",project.getName());
            }
            if (script.getRequestTypeId() != 0 && script.getRequestTypeId() != null){
                RequestType requestType = requestTypeMapper.selectByPrimaryKey(script.getRequestTypeId());
                map.put("requestTypeName",requestType.getName());
            }
            if (script.getProtocolId() != 0 && script.getProtocolId() != null){
                Protocol protocol = protocolMapper.selectByPrimaryKey(script.getProtocolId());
                map.put("protocolName",protocol.getName());
            }
            if (script.getUserId() != 0 && script.getUserId() != null){
                User user = userMapper.selectByPrimaryKey(script.getUserId());
                map.put("userName",user.getName());
            }
            result.add(map);
        }
        return new SuccessResponse(result);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public BaseResponse getScriptController(@PathVariable Integer id){
        return new SuccessResponse(scriptService.getScriptByIdService(id));
    }

    @AuthPermission(PermissionConstant.VIP)
    @RequestMapping(value = "",method = RequestMethod.PUT)
    public BaseResponse updateScriptController(Script script){
        return new SuccessResponse(scriptService.updateScriptService(script));
    }

    @AuthPermission(PermissionConstant.VIP)
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public BaseResponse deleteScriptController(@PathVariable Integer id){
        return new SuccessResponse(scriptService.deleteScriptService(id));
    }

    @RequestMapping(value = "/orderByProject/{projectId}",method = RequestMethod.GET)
    public BaseResponse scriptListByProjectIdController(@PathVariable Integer projectId){
        log.info("获取项目下的脚本列表");
        List<Map> result = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        List<Script> scripts = scriptService.listScriptByProjectIdService(projectId);
        for (Script script:scripts){
            String json = JSONObject.toJSONString(script);
            map = JSONObject.parseObject(json, Map.class);
            if (script.getProjectId() != 0 && script.getProjectId() != null){
                Project project = projectMapper.selectByPrimaryKey(script.getProjectId());
                if (project.getEnv() != 0 && project.getEnv() != null){
                    ProjectEnv projectEnv = projectEnvMapper.selectByPrimaryKey(project.getEnv());
                    log.info("环境:"+projectEnv.getId());
                    map.put("envId",projectEnv.getId());
                    map.put("envName",projectEnv.getName());
                }
                map.put("projectName",project.getName());
            }
            if (script.getRequestTypeId() != 0 && script.getRequestTypeId() != null){
                RequestType requestType = requestTypeMapper.selectByPrimaryKey(script.getRequestTypeId());
                map.put("requestTypeName",requestType.getName());
            }
            if (script.getProtocolId() != 0 && script.getProtocolId() != null){
                Protocol protocol = protocolMapper.selectByPrimaryKey(script.getProtocolId());
                map.put("protocolName",protocol.getName());
            }
            if (script.getUserId() != 0 && script.getUserId() != null){
                User user = userMapper.selectByPrimaryKey(script.getUserId());
                map.put("userName",user.getName());
            }
            result.add(map);
        }
        return new SuccessResponse(result);
    }

    @AuthPermission(PermissionConstant.VIP)
    @RequestMapping(value = "",method = RequestMethod.POST)
    public BaseResponse addScriptController(Script script){
        if (script.getCreateTime() == null){
            script.setCreateTime(new Date());
        }
        if (script.getUpdateTime() == null){
            script.setUpdateTime(new Date());
        }
        try {
            int result = scriptService.addScriptService(script);
            log.info("添加脚本成功");
            return new SuccessResponse(result);
        }catch (Exception e){
            log.info("添加脚本失败:"+e.getMessage());
            return new ErrorResponse(CommonConstant.ADD_SCRIPT_FAIL);
        }

    }

    @RequestMapping(value = "/filter",method = RequestMethod.GET)
    public BaseResponse filterScriptListController(Integer projectId, Integer protocolId, Integer userId){
        log.info("projectId:"+projectId+"protocolId:"+protocolId+"userId:"+userId);
        log.info("获取过滤脚本列表");
        List<Script> scripts = new ArrayList<>();
        if ((projectId == 0)&&(protocolId == 0)&& (userId != 0)){
            scripts = scriptService.filterScriptListByUserIdService(userId);
        }else if ((projectId == 0)&&(protocolId != 0)&&(userId == 0)){
            scripts = scriptService.filterScriptListByProtocolIdService(protocolId);
        }else if ((projectId != 0)&&(protocolId == 0)&&(userId == 0)){
            scripts = scriptService.filterScriptListByProjectIdService(projectId);
        }else if ((projectId != 0)&&(protocolId != 0 )&&( userId == 0)){
            scripts = scriptService.filterScriptListByProjectIdAndProtocolIdService(projectId, protocolId);
        }else if ((projectId != 0)&&(protocolId == 0)&&(userId != 0)){
            scripts = scriptService.filterScriptListByUserIdAndProjectIdService(userId, projectId);
        }else if ((projectId == 0)&&(protocolId != 0)&&(userId != 0)){
            scripts = scriptService.filterScriptListByUserIdAndProtocolIdService(userId, protocolId);
        }else if ((projectId == 0)&&(protocolId == 0)&&(userId == 0)){
            scripts = scriptService.listScriptService();
        }
        else {
            scripts = scriptService.filterScriptListByProjectIdAndProtocolIdAndUserIdService(projectId,protocolId,userId);

        }
        List<Map> listMap = new ArrayList<>();
        if (scripts == null){
            return new SuccessResponse(listMap);
        }
        Map<String,Object> map = new HashMap<>();
        if (scripts.size() > 0){
            for (Script script:scripts){
                String json = JSONObject.toJSONString(script);
                map = JSONObject.parseObject(json, Map.class);
                if (script.getProjectId() != 0 && script.getProjectId() != null){
                    Project project = projectMapper.selectByPrimaryKey(script.getProjectId());
                    if (project.getEnv() != 0 && project.getEnv() != null){
                        ProjectEnv projectEnv = projectEnvMapper.selectByPrimaryKey(project.getEnv());
                        map.put("envId",projectEnv.getId());
                        map.put("envName",projectEnv.getName());
                    }
                    map.put("projectName",project.getName());
                }
                if (script.getRequestTypeId() != 0 && script.getRequestTypeId() != null){
                    RequestType requestType = requestTypeMapper.selectByPrimaryKey(script.getRequestTypeId());
                    map.put("requestTypeName",requestType.getName());
                }
                if (script.getProtocolId() != 0 && script.getProtocolId() != null){
                    Protocol protocol = protocolMapper.selectByPrimaryKey(script.getProtocolId());
                    map.put("protocolName",protocol.getName());
                }
                if (script.getUserId() != 0 && script.getUserId() != null){
                    User user = userMapper.selectByPrimaryKey(script.getUserId());
                    map.put("userName",user.getName());
                }
                listMap.add(map);
            }
        }
        return new SuccessResponse(listMap);
    }

    @RequestMapping(value = "/search",method = RequestMethod.GET)
    public BaseResponse searchScriptListController(String keyword){
        log.info("关键字搜索脚本列表");
        List<Map> listMap = new ArrayList<>();
        List<Script> scripts = scriptService.searchScriptBYKeyWordService(keyword);
        if (scripts == null){
            return new SuccessResponse(listMap);
        }
        Map<String,Object> map = new HashMap<>();
        if (scripts.size() > 0){
            for (Script script:scripts){
                String json = JSONObject.toJSONString(script);
                map = JSONObject.parseObject(json, Map.class);
                if (script.getProjectId() != 0 && script.getProjectId() != null){
                    Project project = projectMapper.selectByPrimaryKey(script.getProjectId());
                    if (project.getEnv() != 0 && project.getEnv() != null){
                        ProjectEnv projectEnv = projectEnvMapper.selectByPrimaryKey(project.getEnv());
                        map.put("envId",projectEnv.getId());
                        map.put("envName",projectEnv.getName());
                    }
                    map.put("projectName",project.getName());
                }
                if (script.getRequestTypeId() != 0 && script.getRequestTypeId() != null){
                    RequestType requestType = requestTypeMapper.selectByPrimaryKey(script.getRequestTypeId());
                    map.put("requestTypeName",requestType.getName());
                }
                if (script.getProtocolId() != 0 && script.getProtocolId() != null){
                    Protocol protocol = protocolMapper.selectByPrimaryKey(script.getProtocolId());
                    map.put("protocolName",protocol.getName());
                }
                if (script.getUserId() != 0 && script.getUserId() != null){
                    User user = userMapper.selectByPrimaryKey(script.getUserId());
                    map.put("userName",user.getName());
                }
                listMap.add(map);
            }
        }
        return new SuccessResponse(listMap);
    }
}
