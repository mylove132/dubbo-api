package com.dubbo.api.controller;

import com.alibaba.fastjson.JSONObject;
import com.dubbo.api.common.bean.BaseResponse;
import com.dubbo.api.common.bean.ErrorResponse;
import com.dubbo.api.common.bean.PageInfo;
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
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Slf4j
@RestController
@RequestMapping("/api/script")
public class ScriptController {

    @Autowired
    private IScriptService scriptService;

    @RequestMapping(value = "",method = RequestMethod.GET)
    public BaseResponse scriptListController(@RequestParam(defaultValue = "1",value = "currentPage") Integer pageNum,
                                             @RequestParam(defaultValue = "10",value = "pageSize") Integer pageSize){
        List scripts = scriptService.listScriptService(pageNum,pageSize);
        return new SuccessResponse(new PageInfo(scripts));
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
    public BaseResponse scriptListByProjectIdController(@PathVariable Integer projectId,@RequestParam(defaultValue = "1",value = "currentPage") Integer pageNum,
                                                        @RequestParam(defaultValue = "10",value = "pageSize") Integer pageSize){
        log.info("获取项目下的脚本列表");
        List scripts = scriptService.listScriptByProjectIdService(projectId,pageNum,pageSize);
        return new SuccessResponse(new PageInfo(scripts));
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
    public BaseResponse filterScriptListController(Integer projectId, Integer protocolId, Integer userId,@RequestParam(defaultValue = "1",value = "currentPage") Integer pageNum,
                                                   @RequestParam(defaultValue = "10",value = "pageSize") Integer pageSize){
        log.info("projectId:"+projectId+"protocolId:"+protocolId+"userId:"+userId);
        log.info("获取过滤脚本列表");
        List scripts = new ArrayList<>();
        if ((projectId == 0)&&(protocolId == 0)&& (userId != 0)){
            scripts = scriptService.filterScriptListByUserIdService(userId,pageNum,pageSize);
        }else if ((projectId == 0)&&(protocolId != 0)&&(userId == 0)){
            scripts = scriptService.filterScriptListByProtocolIdService(protocolId,pageNum,pageSize);
        }else if ((projectId != 0)&&(protocolId == 0)&&(userId == 0)){
            scripts = scriptService.filterScriptListByProjectIdService(projectId,pageNum,pageSize);
        }else if ((projectId != 0)&&(protocolId != 0 )&&( userId == 0)){
            scripts = scriptService.filterScriptListByProjectIdAndProtocolIdService(projectId, protocolId,pageNum,pageSize);
        }else if ((projectId != 0)&&(protocolId == 0)&&(userId != 0)){
            scripts = scriptService.filterScriptListByUserIdAndProjectIdService(userId, projectId,pageNum,pageSize);
        }else if ((projectId == 0)&&(protocolId != 0)&&(userId != 0)){
            scripts = scriptService.filterScriptListByUserIdAndProtocolIdService(userId, protocolId,pageNum,pageSize);
        }else if ((projectId == 0)&&(protocolId == 0)&&(userId == 0)){
            scripts = scriptService.listScriptService(pageNum,pageSize);
        }
        else {
            scripts = scriptService.filterScriptListByProjectIdAndProtocolIdAndUserIdService(projectId,protocolId,userId,pageNum,pageSize);
        }

        return new SuccessResponse(new PageInfo(scripts));
    }

    @RequestMapping(value = "/search",method = RequestMethod.GET)
    public BaseResponse searchScriptListController(String keyword,@RequestParam(defaultValue = "1",value = "currentPage") Integer pageNum,
                                                   @RequestParam(defaultValue = "10",value = "pageSize") Integer pageSize){
        log.info("关键字搜索脚本列表");
        List scripts = scriptService.searchScriptByKeyWord(keyword,pageNum,pageSize);

        return new SuccessResponse(new PageInfo(scripts));
    }
}
