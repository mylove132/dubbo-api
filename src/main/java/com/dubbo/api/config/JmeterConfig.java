package com.dubbo.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2019-07-03:13:59
 * Modify date: 2019-07-03:13:59
 */
@Component
@SpringBootConfiguration
public class JmeterConfig {

    @Value("${JMEMTER_BIN_PATH}")
    private String jmeterBinPath;

    @Value("${JMX_FILE_PATH}")
    private String jmxFilePath;

    @Value("${JTL_FILE_PATH}")
    private String jtlFilePath;

    @Value("${server.url}")
    private String serverUrl;

    @Value("${STATIC_SERVER}")
    private String staticServer;

    @Value("${STATIC_PATH}")
    private String staticPath;

    @Value("${BUCKET}")
    private String BUCKET;

    @Value("${ACCESSKEY}")
    private String ACCESSKEY;

    @Value("${PATH}")
    private String PATH;

    @Value("${SECRETKEY}")
    private String SECRETKEY;

    @Value("${server.port}")
    private String port;

    public String getJmeterBinPath() {
        return jmeterBinPath;
    }

    public void setJmeterBinPath(String jmeterBinPath) {
        this.jmeterBinPath = jmeterBinPath;
    }

    public String getJtlFilePath() {
        return jtlFilePath;
    }

    public void setJtlFilePath(String jtlFilePath) {
        this.jtlFilePath = jtlFilePath;
    }

    public String getStaticServer() {
        return staticServer;
    }

    public void setStaticServer(String staticServer) {
        this.staticServer = staticServer;
    }

    public String getStaticPath() {
        return staticPath;
    }

    public void setStaticPath(String staticPath) {
        this.staticPath = staticPath;
    }

    public String getBUCKET() {
        return BUCKET;
    }

    public void setBUCKET(String BUCKET) {
        this.BUCKET = BUCKET;
    }

    public String getACCESSKEY() {
        return ACCESSKEY;
    }

    public void setACCESSKEY(String ACCESSKEY) {
        this.ACCESSKEY = ACCESSKEY;
    }

    public String getPATH() {
        return PATH;
    }

    public void setPATH(String PATH) {
        this.PATH = PATH;
    }

    public String getSECRETKEY() {
        return SECRETKEY;
    }

    public void setSECRETKEY(String SECRETKEY) {
        this.SECRETKEY = SECRETKEY;
    }

    public String getJmxFilePath() {
        return jmxFilePath;
    }

    public void setJmxFilePath(String jmxFilePath) {
        this.jmxFilePath = jmxFilePath;
    }



    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}
