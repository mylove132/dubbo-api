package com.dubbo.api.common.exception;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2019-06-28:15:05
 * Modify date: 2019-06-28:15:05
 */
public class UnknowException extends RuntimeException {
    private int code;  //异常状态码

    private String message;  //异常信息

    private String method;   //发生的方法，位置等

    private String descinfo;   //描述

    public UnknowException(int code, String message, String method, String descinfo) {
        this.code=code;
        this.message=message;
        this.method=method;
        this.descinfo=descinfo;
    }
    public UnknowException(String method, String descinfo) {
        this.code=-1;
        this.message="未知异常";
        this.method=method;
        this.descinfo=descinfo;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getDescinfo() {
        return descinfo;
    }

    public void setDescinfo(String descinfo) {
        this.descinfo = descinfo;
    }
}
