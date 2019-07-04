package com.dubbo.api.common.bean;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2019-07-03:17:52
 * Modify date: 2019-07-03:17:52
 */
public class SocketMessage {

    public String message;

    public String date;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}