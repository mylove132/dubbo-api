package com.dubbo.api.service;

import com.dubbo.api.vo.ScheduledTaskBean;

import java.util.List;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2019-06-17:13:09
 * Modify date: 2019-06-17:13:09
 */
public interface ScheduledTaskService {

    /**
     * 所有任务列表
     */
    List<ScheduledTaskBean> taskList();

    /**
     * 根据任务key 启动任务
     */
    Boolean start(String taskKey);

    /**
     * 根据任务key 停止任务
     */
    Boolean stop(String taskKey);

    /**
     * 根据任务key 重启任务
     */
    Boolean restart(String taskKey);


    /**
     * 程序启动时初始化  ==> 启动所有正常状态的任务
     */
    void initAllTask(List<ScheduledTaskBean> scheduledTaskBeanList);


    /**
     * 创建定时任务
     * @param scheduledTaskBean
     * @return
     */
    boolean createTask(ScheduledTaskBean scheduledTaskBean);

    /**
     * 更新定时任务
     * @param scheduledTaskBean
     * @return
     */
    boolean updateTask(ScheduledTaskBean scheduledTaskBean);

}