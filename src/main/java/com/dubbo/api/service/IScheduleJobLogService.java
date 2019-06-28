package com.dubbo.api.service;


import com.dubbo.api.common.bean.BaseResponse;
import com.dubbo.api.vo.ScheduleJobLog;

public interface IScheduleJobLogService {

    BaseResponse insertSelective(ScheduleJobLog log);

}
