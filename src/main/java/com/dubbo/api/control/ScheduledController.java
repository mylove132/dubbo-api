package com.dubbo.api.control;

import com.dubbo.api.response.CommonResponse;
import com.dubbo.api.response.Response;
import com.dubbo.api.service.ScheduledTaskService;
import com.dubbo.api.vo.ScheduledTaskBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/scheduled")
public class ScheduledController {
    @Autowired
    private ScheduledTaskService scheduledTaskService;
    /**
     * 所有任务列表
     */
    @RequestMapping("/taskList")
    public Response<List<ScheduledTaskBean>> taskList() {
        return CommonResponse.makeOKRsp(scheduledTaskService.taskList());
    }

    /**
     * 根据任务key => 启动任务
     */
    @RequestMapping("/start")
    public Response<Boolean> start(@RequestParam("taskKey") String taskKey) {
        boolean result = scheduledTaskService.start(taskKey);
        return CommonResponse.makeOKRsp(result);
    }

    /**
     * 根据任务key => 停止任务
     */
    @RequestMapping("/stop")
    public Response<Boolean> stop(@RequestParam("taskKey") String taskKey) {
        boolean result = scheduledTaskService.stop(taskKey);
        return CommonResponse.makeOKRsp(result);
    }

    /**
     * 根据任务key => 重启任务
     */
    @RequestMapping("/restart")
    public Response<Boolean> restart(@RequestParam("taskKey") String taskKey) {
        boolean result = scheduledTaskService.restart(taskKey);
        return CommonResponse.makeOKRsp(result);
    }

}
