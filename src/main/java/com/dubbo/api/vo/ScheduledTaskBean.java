package com.dubbo.api.vo;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2019-06-17:11:19
 * Modify date: 2019-06-17:11:19
 */
public class ScheduledTaskBean {

    private String taskKey;
    /**
     * 任务描述
     */
    private String taskDesc;
    /**
     * 任务表达式
     */
    private String taskCron;

    /**
     * 程序初始化是否启动 1 是 0 否
     */
    private Integer initStartFlag;

    /**
     * 当前是否已启动
     */
    private boolean startFlag;
    /**
     * 关联的脚本id
     */
    private Integer scriptId;

    public String getTaskKey() {
        return taskKey;
    }

    public void setTaskKey(String taskKey) {
        this.taskKey = taskKey;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    public String getTaskCron() {
        return taskCron;
    }

    public void setTaskCron(String taskCron) {
        this.taskCron = taskCron;
    }

    public Integer getInitStartFlag() {
        return initStartFlag;
    }

    public void setInitStartFlag(Integer initStartFlag) {
        this.initStartFlag = initStartFlag;
    }

    public boolean isStartFlag() {
        return startFlag;
    }

    public void setStartFlag(boolean startFlag) {
        this.startFlag = startFlag;
    }

    public Integer getScriptId() {
        return scriptId;
    }

    public void setScriptId(Integer scriptId) {
        this.scriptId = scriptId;
    }

    @Override
    public String toString() {
        return "ScheduledTaskBean{" +
                "taskKey='" + taskKey + '\'' +
                ", taskDesc='" + taskDesc + '\'' +
                ", taskCron='" + taskCron + '\'' +
                ", initStartFlag=" + initStartFlag +
                ", startFlag=" + startFlag +
                ", scriptId=" + scriptId +
                '}';
    }
}
