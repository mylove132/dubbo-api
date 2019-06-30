package com.dubbo.api.vo;

import java.io.Serializable;
import java.util.Date;

public class Project implements Serializable {
    private Integer id;

    private String name;

    private Integer env;

    private Integer type;

    private String descption;

    private Date ctime;

    private Date updateTime;

    private Integer userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getEnv() {
        return env;
    }

    public void setEnv(Integer env) {
        this.env = env;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDescption() {
        return descption;
    }

    public void setDescption(String descption) {
        this.descption = descption == null ? null : descption.trim();
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", env=" + env +
                ", type=" + type +
                ", descption='" + descption + '\'' +
                ", ctime=" + ctime +
                ", updateTime=" + updateTime +
                ", userId=" + userId +
                '}';
    }
}