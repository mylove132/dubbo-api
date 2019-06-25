package com.dubbo.api.service;


import com.dubbo.api.common.bean.BaseResponse;
import com.dubbo.api.model.ScheduleJobLog;

public interface IScheduleJobLogService {

    BaseResponse insertSelective(ScheduleJobLog log);

}
