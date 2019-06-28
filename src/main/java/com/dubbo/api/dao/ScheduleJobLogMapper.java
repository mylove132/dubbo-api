package com.dubbo.api.dao;
import com.dubbo.api.vo.ScheduleJobLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * 定时任务日志
 *
 */
@Mapper
public interface ScheduleJobLogMapper {

    int insertSelective(ScheduleJobLog record);

}
