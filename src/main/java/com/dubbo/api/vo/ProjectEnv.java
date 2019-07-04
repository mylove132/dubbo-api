package com.dubbo.api.vo;

public class ProjectEnv {
    private Integer id;

    private String name;

    private String zk;

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

    public String getZk() {
        return zk;
    }

    public void setZk(String zk) {
        this.zk = zk == null ? null : zk.trim();
    }
}