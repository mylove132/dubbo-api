package com.dubbo.api.controller;

import com.dubbo.api.common.bean.BaseResponse;
import com.dubbo.api.common.bean.SuccessResponse;
import com.dubbo.api.service.IScriptService;
import com.dubbo.api.vo.Script;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/api/script")
public class ScriptController {

    @Autowired
    private IScriptService scriptService;

    @RequestMapping(value = "",method = RequestMethod.GET)
    public BaseResponse scriptListController(){
        return new SuccessResponse(scriptService.listScriptService());
    }

    @RequestMapping(value = "?projectId={projectId}",method = RequestMethod.GET)
    public BaseResponse scriptListByProjectIdController(@PathVariable Integer projectId){
        return new SuccessResponse(scriptService.listScriptByProjectIdService(projectId));
    }

    @RequestMapping(value = "",method = RequestMethod.POST)
    public BaseResponse addScriptController(Script script){
        if (script.getCreateTime() == null){
            script.setCreateTime(new Date());
        }
        if (script.getUpdateTime() == null){
            script.setUpdateTime(new Date());
        }
        scriptService.addScriptService(script);
        return null;
    }
}
