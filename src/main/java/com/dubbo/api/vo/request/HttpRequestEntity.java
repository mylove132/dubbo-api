package com.dubbo.api.vo.request;

import java.io.Serializable;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2019-07-29:16:57
 * Modify date: 2019-07-29:16:57
 */
public class HttpRequestEntity implements Serializable {

    private String url;
    private String username;
    private String password;
//    private String lt;
//    private String execution;
//    private String platformType;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public String getLt() {
//        return lt;
//    }
//
//    public void setLt(String lt) {
//        this.lt = lt;
//    }
//
//    public String getExecution() {
//        return execution;
//    }
//
//    public void setExecution(String execution) {
//        this.execution = execution;
//    }
//
//    public String getPlatformType() {
//        return platformType == null?"teacher":platformType;
//    }
//
//    public void setPlatformType(String platformType) {
//        this.platformType = platformType;
//    }
}
