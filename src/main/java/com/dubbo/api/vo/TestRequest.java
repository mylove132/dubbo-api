package com.dubbo.api.vo;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class TestRequest implements Serializable {

    @NotBlank(message = "url不能为空")
    private String url;

    @Max(value = 3,message = "用户协议（1～3）")
    private Integer protocolId;

    private String cookie;

    private String header;

    @Max(value = 3,message = "请求方式（1～3）")
    private Integer requestTypeId;

    private String params;

    private Integer timeOut;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getProtocolId() {
        return protocolId;
    }

    public void setProtocolId(Integer protocolId) {
        this.protocolId = protocolId;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public Integer getRequestTypeId() {
        return requestTypeId;
    }

    public void setRequestTypeId(Integer requestTypeId) {
        this.requestTypeId = requestTypeId;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public Integer getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(Integer timeOut) {
        this.timeOut = timeOut;
    }

    @Override
    public String toString() {
        return "TestRequest{" +
                "url='" + url + '\'' +
                ", protocolId=" + protocolId +
                ", cookie='" + cookie + '\'' +
                ", header='" + header + '\'' +
                ", requestTypeId=" + requestTypeId +
                ", params='" + params + '\'' +
                ", timeOut=" + timeOut +
                '}';
    }
}
