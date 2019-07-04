package com.dubbo.api.controller;

import com.dubbo.api.common.bean.BaseResponse;
import com.dubbo.api.common.bean.ErrorResponse;
import com.dubbo.api.common.bean.SuccessResponse;
import com.dubbo.api.common.constant.CommonConstant;
import com.dubbo.api.common.util.DateUtil;
import com.dubbo.api.dao.HistoryMapper;
import com.dubbo.api.dao.ProjectMapper;
import com.dubbo.api.dao.ScriptMapper;
import com.dubbo.api.dao.UserMapper;
import com.dubbo.api.vo.History;
import com.dubbo.api.vo.Project;
import com.dubbo.api.vo.Script;
import com.dubbo.api.vo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2019-07-04:13:14
 * Modify date: 2019-07-04:13:14
 */
@Slf4j
@RestController
@RequestMapping("/api/history")
public class HistoryController {

    @Autowired
    private HistoryMapper historyMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private ScriptMapper scriptMapper;

    @RequestMapping(value = "",method = RequestMethod.GET)
    public BaseResponse listHistoryCrontroller(){
        List<Map<String,Object>> mapList = new ArrayList<>();
        List<History> historyList = historyMapper.listHistory();
        if (historyList != null && historyList.size()>0){
            for (History history:historyList){
                Map<String,Object> result = new HashMap<>();
                Integer userId = history.getUserId();
                User user = null;
                Script script = null;
                Project project = null;
                if (userId > 0){
                    user = userMapper.selectByPrimaryKey(userId);
                }
                if (user == null){
                    result.put("userName",null);
                }else {
                    result.put("userName",user.getName());
                }
                Integer scriptId = history.getScriptId();
                if (scriptId > 0){
                    script = scriptMapper.selectByPrimaryKey(scriptId);
                }
                if (script == null){
                    result.put("scriptName",null);
                }else {
                    result.put("scriptName",script.getName());
                }
                Integer projectId = script.getProjectId();
                if (projectId > 0){
                    project = projectMapper.selectByPrimaryKey(projectId);
                }
                if (project == null){
                    result.put("projectName",null);
                }else {
                    result.put("projectName",project.getName());
                }
                result.put("md5",history.getMd5());
                result.put("id",history.getId());
                result.put("status",history.getStatus());
                result.put("createTime", DateUtil.dateFormate(history.getCreateTime()));
                mapList.add(result);
            }
            return new SuccessResponse(mapList);
        }else {
            return new ErrorResponse(CommonConstant.OP_FAILED);
        }
    }
    @RequestMapping(method = RequestMethod.GET,value = "/orderByScriptId/{scriptId}")
    public BaseResponse listHistoryByScriptIdCrontoller(@PathVariable Integer scriptId){
        log.info("通过脚本id过滤历史记录");
        List<Map<String,Object>> mapList = new ArrayList<>();
        List<History> histories = historyMapper.listHistoryByScriptId(scriptId);
        for (History history:histories){
            Map<String,Object> result = new HashMap<>();
            Integer userId = history.getUserId();
            User user = null;
            Script script = null;
            Project project = null;
            if (userId > 0){
                user = userMapper.selectByPrimaryKey(userId);
            }
            if (user == null){
                result.put("userName",null);
            }else {
                result.put("userName",user.getName());
            }
            if (scriptId > 0){
                script = scriptMapper.selectByPrimaryKey(scriptId);
            }
            if (script == null){
                result.put("scriptName",null);
            }else {
                result.put("scriptName",script.getName());
            }
            Integer projectId = script.getProjectId();
            if (projectId > 0){
                project = projectMapper.selectByPrimaryKey(projectId);
            }
            if (project == null){
                result.put("projectName",null);
            }else {
                result.put("projectName",project.getName());
            }
            result.put("md5",history.getMd5());
            result.put("id",history.getId());
            result.put("status",history.getStatus());
            result.put("createTime", DateUtil.dateFormate(history.getCreateTime()));
            mapList.add(result);
        }
        return new SuccessResponse(mapList);
    }

    @RequestMapping(method = RequestMethod.GET,value = "/search")
    public BaseResponse searchHistoryCrontoller(String keyword){
        List<Map<String,Object>> mapList = new ArrayList<>();
        List<Script> scripts = scriptMapper.searchScriptByKeyWord(keyword);
        if (scripts != null && scripts.size()>0){
            for (Script script:scripts){
                Integer scriptId = script.getId();
                if (scriptId > 0){
                    List<History> histories = historyMapper.listHistoryByScriptId(scriptId);
                    for (History history:histories){
                        Map<String,Object> result = new HashMap<>();
                        Integer userId = history.getUserId();
                        User user = null;
                        Project project = null;
                        if (userId > 0){
                            user = userMapper.selectByPrimaryKey(userId);
                        }
                        if (user == null){
                            result.put("userName",null);
                        }else {
                            result.put("userName",user.getName());
                        }
                        result.put("scriptName",script.getName());
                        Integer projectId = script.getProjectId();
                        if (projectId > 0){
                            project = projectMapper.selectByPrimaryKey(projectId);
                        }
                        if (project == null){
                            result.put("projectName",null);
                        }else {
                            result.put("projectName",project.getName());
                        }
                        result.put("md5",history.getMd5());
                        result.put("id",history.getId());
                        result.put("status",history.getStatus());
                        result.put("createTime", DateUtil.dateFormate(history.getCreateTime()));
                        mapList.add(result);
                    }
                }
            }
        }
        return new SuccessResponse(mapList);
    }
}
