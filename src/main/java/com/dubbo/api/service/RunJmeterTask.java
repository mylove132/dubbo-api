package com.dubbo.api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2019-06-17:13:06
 * Modify date: 2019-06-17:13:06
 */
public class RunJmeterTask implements ScheduledTaskJob {

    private static final Logger LOGGER = LoggerFactory.getLogger(RunJmeterTask.class);

    @Override
    public void run() {
        LOGGER.info("ScheduledTask => 01  run  当前线程名称 {} ", Thread.currentThread().getName());
    }
}
