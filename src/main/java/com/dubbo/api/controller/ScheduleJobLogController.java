package com.dubbo.api.controller;

import com.dubbo.api.common.bean.BaseResponse;
import com.dubbo.api.vo.ScheduleJobLog;
import com.dubbo.api.service.impl.ScheduleJobLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 定时任务日志
 *
 */
@RestController
@RequestMapping("/api/scheduleLog")
public class ScheduleJobLogController {

    @Autowired
    private ScheduleJobLogService scheduleJobLogService;
    /**
     * 查询列表
     *
     * @param scheduleJobLog
     * @return
     */
    @RequestMapping(value = "",method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse list(@RequestBody ScheduleJobLog scheduleJobLog){
        return scheduleJobLogService.insertSelective(scheduleJobLog);
    }
}
