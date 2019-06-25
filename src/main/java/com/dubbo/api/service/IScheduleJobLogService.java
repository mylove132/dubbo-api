package com.dubbo.api.service;


import com.dubbo.api.model.ScheduleJobLog;

public interface IScheduleJobLogService {

    void insertSelective(ScheduleJobLog log);

}
