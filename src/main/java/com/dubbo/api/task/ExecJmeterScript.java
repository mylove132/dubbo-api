package com.dubbo.api.task;
import com.alibaba.fastjson.JSONObject;
import com.dubbo.api.common.bean.DubboJmeterScript;
import com.dubbo.api.common.bean.ErrorResponse;
import com.dubbo.api.common.bean.HttpJmeterScript;
import com.dubbo.api.common.constant.CommonConstant;
import com.dubbo.api.common.util.HttpClientUtil;
import com.dubbo.api.common.util.MD5Util;
import com.dubbo.api.common.util.RequestClientInterface;
import com.dubbo.api.config.JmeterConfig;
import com.dubbo.api.dao.*;
import com.dubbo.api.service.RedisService;
import com.dubbo.api.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.utils.HttpTinyClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2019-06-25:14:18
 * Modify date: 2019-06-25:14:18
 */
@Slf4j
@Component
public class ExecJmeterScript {

    @Autowired
    private HistoryMapper historyMapper;

    @Autowired
    private JmeterConfig jmeterConfig;

    @Autowired
    private RedisService redisService;
    @Autowired
    private ScriptMapper scriptMapper;

    @Autowired
    private RequestTypeMapper requestTypeMapper;

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private ProjectEnvMapper projectEnvMapper;

    public void execJmeterScript(String userAndId){
        String userId = userAndId.split(",")[0];
        String scriptId = userAndId.split(",")[1];
        log.info("执行jmeter脚本id:" + scriptId);
        String md5 = MD5Util.encrypt(UUID.randomUUID().toString());
        String jmeterRedisId = redisService.get("exec_jmeter_id_" + scriptId);
        String jmeterCountRedisId = redisService.get("exec_jmeter_count");
        if (jmeterRedisId == null) {
            redisService.set("exec_jmeter_id_" + scriptId, String.valueOf(scriptId));
        } else {
            log.error("执行的jmeter脚本"+scriptId+"已经在执行");
            return;
        }
        if (jmeterCountRedisId == null) {
            redisService.set("exec_jmeter_count", String.valueOf(1));
        } else if (Integer.parseInt(jmeterCountRedisId) >= 2) {
            log.error("执行的jmeter脚本"+scriptId+"已经在执行");
            return;
        } else {
            String value = String.valueOf(Integer.parseInt(redisService.get("exec_jmeter_count")) + 1);
            redisService.set("exec_jmeter_count", value);
        }
        Script script = scriptMapper.selectByPrimaryKey(Integer.parseInt(scriptId));
        String scriptName = script.getName();
        Integer protocolId = script.getProtocolId();
        Integer preNumber = script.getPreNumber();
        Integer preTime = script.getPreTime();
        String url = script.getUrl();
        Integer requestTypeid = script.getRequestTypeId();
        RequestType requestType = null;
        if (requestTypeid > 0) {
            requestType = requestTypeMapper.selectByPrimaryKey(requestTypeid);
        }
        String port = "";
        try {
            port = script.getPort().toString();
        }catch (Exception e){
            port = null;
        }

        String params = script.getParams();
        History history = new History();
        history.setCreateTime(new Date());
        history.setMd5(md5);
        history.setScriptId(Integer.parseInt(scriptId));
        history.setUserId(Integer.parseInt(userId));

        log.info("创建时间:"+history.getCreateTime().toString());

        if (protocolId == 1) {
            log.info("http请求，创建jmx文件");
            String fileContent = createHttpJmxFile(preNumber, preTime, scriptName, url, requestType.getName(), params, script.getTimeOut().toString(),
                    script.getHeader(), script.getCookie(), script.getAssertText(), script.getIp(), port);
            String jmxFilePath = jmeterConfig.getJmxFilePath() + md5 + ".jmx";
            try {
                OutputStream os = new FileOutputStream(new File(jmxFilePath));
                os.write(fileContent.getBytes("utf-8"));
                os.close();
            } catch (IOException e) {
                log.error("创建jmx文件出错：" + e.getMessage());
                redisService.remove("exec_jmeter_id_" + scriptId);
                Integer redisExecCount = Integer.parseInt(redisService.get("exec_jmeter_count"));
                if (redisExecCount > 0){
                    redisService.set("exec_jmeter_count",String.valueOf(redisExecCount-1));
                }else {
                    redisService.set("exec_jmeter_count","0");
                }
                log.error("http jmx文件：" + jmxFilePath + "创建失败");
                history.setStatus("fail");
                historyMapper.insertSelective(history);
                log.error("创建meter文件失败");
                return;
            }
            File file = new File(jmxFilePath);
            if (!file.exists()) {
                log.error("http jmx文件：" + jmxFilePath + "不存在");
                history.setStatus("fail");
                historyMapper.insert(history);
                log.error("执行的meter文件不存在："+md5+".jmx");
                return;
            }
            new Thread(new Runnable() {
                @Override
                public void run() {
                    File jmeterHtmlFilePath = new File(jmeterConfig.getJmeterHtmlPath()+md5);
                    if (!(jmeterHtmlFilePath.exists() && jmeterHtmlFilePath.isDirectory())){
                        jmeterHtmlFilePath.mkdir();
                    }
                    String cmd = "%s -n -t %s -l %s -e -o %s" ;
                    cmd = String.format(cmd,jmeterConfig.getJmeterBinPath(),jmeterConfig.getJmxFilePath()+md5+".jmx",jmeterConfig.getJtlFilePath()+md5+".jtl",jmeterHtmlFilePath.getAbsolutePath());
                    log.info("执行的命令:"+cmd);
                    String[] listCmd = cmd.split(" ");
                    try {
                        Process process = Runtime.getRuntime().exec(listCmd);
                        process.waitFor();
                        SequenceInputStream sis = new SequenceInputStream(process.getInputStream(), process.getErrorStream());
                        BufferedReader br = new BufferedReader(new InputStreamReader(sis, "utf-8"));
                        StringBuilder result = new StringBuilder();
                        String line;
                        while ((line = br.readLine()) != null) {
                            log.info(line);
                        }
                        if (br != null) {
                            br.close();
                            redisService.remove("exec_jmeter_id_" + scriptId);
                            Integer redisExecCount = Integer.parseInt(redisService.get("exec_jmeter_count"));
                            if (redisExecCount > 0){
                                redisService.set("exec_jmeter_count",String.valueOf(redisExecCount-1));
                            }else {
                                redisService.set("exec_jmeter_count","0");
                            }
                        }
                    } catch (Exception e) {
                        log.error("执行build文件出错：" + e.getMessage());
                        redisService.remove("exec_jmeter_id_" + scriptId);
                        Integer redisExecCount = Integer.parseInt(redisService.get("exec_jmeter_count"));
                        if (redisExecCount > 0){
                            redisService.set("exec_jmeter_count",String.valueOf(redisExecCount-1));
                        }else {
                            redisService.set("exec_jmeter_count","0");
                        }
                        log.error("执行jmx文件出错:" + jmeterConfig.getJmxFilePath() + md5 + ".jmx");
                    }

                }
            }).start();
        }else if (protocolId == 2){
            Integer projectId = script.getProjectId();
            Project project = null;
            if (projectId != null && projectId > 0) {
                project = projectMapper.selectByPrimaryKey(projectId);
            }
            if (project == null) {
                return;
            }
            Integer envId = project.getEnv();
            ProjectEnv projectEnv = null;
            if (envId != null && envId > 0) {
                projectEnv = projectEnvMapper.selectByPrimaryKey(envId);
            }
            if (projectEnv == null) {
                return;
            }
            String zkAddress = projectEnv.getZk();
            log.info("开始创建文件");
            String fileContent = null;
            JSONObject jsonObject = JSONObject.parseObject(params);
            if (!jsonObject.isEmpty()){
                String requestId = jsonObject.getString("reqId");
                if (requestId == null||requestId == ""){
                    jsonObject.put("reqId","qa_${requestid}");
                }
            }
            try {
                fileContent = DubboJmeterScript.startSetting() + DubboJmeterScript.crontrolSetting(preNumber, preTime) +
                        DubboJmeterScript.dubboSetting(script.getName(), zkAddress, script.getTimeOut().toString(), script.getVersion(),
                                script.getIns(), script.getMethod(), script.getParamType(), jsonObject.toJSONString()) +
                        DubboJmeterScript.resultTreeSetting() + DubboJmeterScript.aggregateReportSetting()+
                        DubboJmeterScript.responseAssertSetting(script.getAssertText())+DubboJmeterScript.preProccessorSetting()+
                        DubboJmeterScript.backendListenerSetting()+DubboJmeterScript.endSetting();
            }catch (Exception e){
                log.info("创建文件失败");
                log.info(e.getMessage());
            }
            String jmxFilePath = jmeterConfig.getJmxFilePath() + md5 + ".jmx";
            log.info("jmeter file path:"+jmxFilePath);
            try {
                OutputStream os = new FileOutputStream(new File(jmxFilePath));
                os.write(fileContent.getBytes("utf-8"));
                os.close();
            } catch (IOException e) {
                log.error("dubbo jmx文件写入错误");
                log.error("创建jmx文件出错：" + e.getMessage());
                redisService.remove("exec_jmeter_id_" + scriptId);
                Integer redisExecCount = Integer.parseInt(redisService.get("exec_jmeter_count"));
                if (redisExecCount > 0){
                    redisService.set("exec_jmeter_count",String.valueOf(redisExecCount-1));
                }else {
                    redisService.set("exec_jmeter_count","0");
                }
                log.error("dubbo jmx文件：" + jmxFilePath + "创建失败");
                history.setStatus("fail");
                historyMapper.insertSelective(history);
                log.error("创建meter文件失败");
                return;
            }
            File file = new File(jmxFilePath);
            if (!file.exists()) {
                log.error("dubbo jmx文件不存在");
                redisService.remove("exec_jmeter_id_" + scriptId);
                Integer redisExecCount = Integer.parseInt(redisService.get("exec_jmeter_count"));
                if (redisExecCount > 0){
                    redisService.set("exec_jmeter_count",String.valueOf(redisExecCount-1));
                }else {
                    redisService.set("exec_jmeter_count","0");
                }
                log.error("dubbo jmx文件：" + jmxFilePath + "创建失败");
                history.setStatus("fail");
                historyMapper.insertSelective(history);
                log.error("执行meter文件不存在");
                return;
            }
            new Thread(new Runnable() {
                @Override
                public void run() {
                    File jmeterHtmlFilePath = new File(jmeterConfig.getJmeterHtmlPath()+md5);
                    if (!(jmeterHtmlFilePath.exists() && jmeterHtmlFilePath.isDirectory())){
                        jmeterHtmlFilePath.mkdir();
                    }
                    String cmd = "%s -n -t %s -l %s -e -o %s" ;
                    cmd = String.format(cmd,jmeterConfig.getJmeterBinPath(),jmeterConfig.getJmxFilePath()+md5+".jmx",
                            jmeterConfig.getJtlFilePath()+md5+".jtl",jmeterHtmlFilePath.getAbsolutePath());
                    log.info("执行的命令:"+cmd);
                    String[] listCmd = cmd.split(" ");
                    try {
                        Process process = Runtime.getRuntime().exec(listCmd);
                        process.waitFor();
                        SequenceInputStream sis = new SequenceInputStream(process.getInputStream(), process.getErrorStream());
                        BufferedReader br = new BufferedReader(new InputStreamReader(sis, "utf-8"));
                        StringBuilder result = new StringBuilder();
                        String line;
                        while ((line = br.readLine()) != null) {
                            log.info(line);
                        }
                        if (br != null) {
                            br.close();
                            redisService.remove("exec_jmeter_id_" + scriptId);
                            Integer redisExecCount = Integer.parseInt(redisService.get("exec_jmeter_count"));
                            if (redisExecCount > 0){
                                redisService.set("exec_jmeter_count",String.valueOf(redisExecCount-1));
                            }else {
                                redisService.set("exec_jmeter_count","0");
                            }
                        }
                    } catch (Exception e) {
                        log.error("执行shell命令出错：" + e.getMessage());
                        redisService.remove("exec_jmeter_id_" + scriptId);
                        Integer redisExecCount = Integer.parseInt(redisService.get("exec_jmeter_count"));
                        if (redisExecCount > 0){
                            redisService.set("exec_jmeter_count",String.valueOf(redisExecCount-1));
                        }else {
                            redisService.set("exec_jmeter_count","0");
                        }
                        log.error("执行jmx文件出错:" + jmeterConfig.getJmxFilePath() + md5 + ".jmx");
                    }

                }
            }).start();
        }
        history.setStatus("success");
        historyMapper.insertSelective(history);
    }

    private static String createHttpJmxFile(Integer preNumber, Integer preTime, String interfaceName, String url,
                                            String requestType, String params, String timeOut, String header, String cookie, String assertText, String ip, String port) {
        String fileContent = HttpJmeterScript.headerSetting() + HttpJmeterScript.crontrolSetting(preNumber, preTime) +
                HttpJmeterScript.httpRequestSetting(interfaceName, url, requestType, params, timeOut) + HttpJmeterScript.headerSetting(header) +
                HttpJmeterScript.cookieSetting(cookie, url) + HttpJmeterScript.preProcessorSetting() +
                HttpJmeterScript.resultTreeSetting()+HttpJmeterScript.aggregateGraphSetting()+HttpJmeterScript.responseAssertSetting(assertText)+
                HttpJmeterScript.backendListener(true)+HttpJmeterScript.endSetting() ;
        return fileContent;
    }

    public static void main(String[] args) {
        String url = "http://127.0.0.1:8901/api/common/execScript?scriptId="+30+"&userId="+1;
        System.out.println(url);
        String result = HttpClientUtil.doGet(url);
        System.out.println(result);
    }
}
