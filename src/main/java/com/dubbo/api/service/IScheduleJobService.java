package com.dubbo.api.service;

import com.dubbo.api.common.bean.BaseResponse;
import com.dubbo.api.vo.ScheduleJob;

import java.util.List;

public interface IScheduleJobService {

    BaseResponse selectByQuery();

    BaseResponse add(ScheduleJob scheduleJob);

    BaseResponse update(int id, ScheduleJob scheduleJob);

    BaseResponse deleteBatch(List<Integer> ids);

    int updateBatch(List<Integer> ids, int status);

    BaseResponse run(List<Integer> ids);

    BaseResponse pause(List<Integer> ids);

    BaseResponse resume(List<Integer> ids);

}
