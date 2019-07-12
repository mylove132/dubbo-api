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

    @Value("${RUN_LOG_PATH}")
    private String runLogPath;

    @Value("${REPORT_IMG_PATH}")
    private String reportImgPath;

    @Value("${REPORT_CSV_PATH}")
    private String reportCsvPath;

    @Value("${GENERATE_SCRIPT_PATH}")
    private String generateScriptPath;

    @Value("${STATIC_SERVER}")
    private String staticServer;

    @Value("${JMX_BUILD_FILE_PATH}")
    private String jmeterBuildFilePath;

    @Value("${JMETER_PATH}")
    private String jmeterPath;

    @Value("${JMETER_HTML_PATH}")
    private String jmeterHtmlPath;

    @Value("${ANT_BIN_PATH}")
    private String antBinPath;

    public String getJmeterBinPath() {
        return jmeterBinPath;
    }

    public void setJmeterBinPath(String jmeterBinPath) {
        this.jmeterBinPath = jmeterBinPath;
    }

    public String getJmxFilePath() {
        return jmxFilePath;
    }

    public void setJmxFilePath(String jmxFilePath) {
        this.jmxFilePath = jmxFilePath;
    }

    public String getJtlFilePath() {
        return jtlFilePath;
    }

    public void setJtlFilePath(String jtlFilePath) {
        this.jtlFilePath = jtlFilePath;
    }

    public String getRunLogPath() {
        return runLogPath;
    }

    public void setRunLogPath(String runLogPath) {
        this.runLogPath = runLogPath;
    }

    public String getReportImgPath() {
        return reportImgPath;
    }

    public void setReportImgPath(String reportImgPath) {
        this.reportImgPath = reportImgPath;
    }

    public String getReportCsvPath() {
        return reportCsvPath;
    }

    public void setReportCsvPath(String reportCsvPath) {
        this.reportCsvPath = reportCsvPath;
    }

    public String getGenerateScriptPath() {
        return generateScriptPath;
    }

    public void setGenerateScriptPath(String generateScriptPath) {
        this.generateScriptPath = generateScriptPath;
    }

    public String getStaticServer() {
        return staticServer;
    }

    public void setStaticServer(String staticServer) {
        this.staticServer = staticServer;
    }

    public String getJmeterBuildFilePath() {
        return jmeterBuildFilePath;
    }

    public void setJmeterBuildFilePath(String jmeterBuildFilePath) {
        this.jmeterBuildFilePath = jmeterBuildFilePath;
    }

    public String getJmeterPath() {
        return jmeterPath;
    }

    public void setJmeterPath(String jmeterPath) {
        this.jmeterPath = jmeterPath;
    }

    public String getJmeterHtmlPath() {
        return jmeterHtmlPath;
    }

    public void setJmeterHtmlPath(String jmeterHtmlPath) {
        this.jmeterHtmlPath = jmeterHtmlPath;
    }

    public String getAntBinPath() {
        return antBinPath;
    }

    public void setAntBinPath(String antBinPath) {
        this.antBinPath = antBinPath;
    }
}
