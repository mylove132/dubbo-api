package com.dubbo.api.service.impl;

import com.dubbo.api.common.bean.BaseResponse;
import com.dubbo.api.common.bean.SuccessResponse;
import com.dubbo.api.dao.ScheduleJobLogMapper;
import com.dubbo.api.model.ScheduleJobLog;
import com.dubbo.api.service.IScheduleJobLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class ScheduleJobLogService implements IScheduleJobLogService {

    @Autowired
    private ScheduleJobLogMapper scheduleJobLogMapper;

    @Override
    public BaseResponse insertSelective(ScheduleJobLog log) {
        scheduleJobLogMapper.insertSelective(log);
        return new SuccessResponse(0);
    }
}
