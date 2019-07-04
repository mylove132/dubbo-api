package com.dubbo.api.controller;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.dubbo.api.common.bean.*;
import com.dubbo.api.common.constant.CommonConstant;
import com.dubbo.api.common.util.HttpClientUtil;
import com.dubbo.api.common.util.MD5Util;
import com.dubbo.api.config.JmeterConfig;
import com.dubbo.api.dao.*;
import com.dubbo.api.service.RedisService;
import com.dubbo.api.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/common")
public class CommonController {

    @Autowired
    private JmeterConfig jmeterConfig;

    @Autowired
    private RedisService redisService;
    @Autowired
    private ProjectTypeMapper projectTypeMapper;

    @Autowired
    private ProjectEnvMapper projectEnvMapper;

    @Autowired
    private ProtocolMapper protocolMapper;

    @Autowired
    private RequestTypeMapper requestTypeMapper;

    @Autowired
    private ScriptMapper scriptMapper;

    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private HistoryMapper historyMapper;

    @RequestMapping(value = "/platform", method = RequestMethod.GET)
    public BaseResponse platformListController() {
        log.info("获取平台列表");
        return new SuccessResponse(projectTypeMapper.projectTypeList());
    }

    @RequestMapping(value = "/env", method = RequestMethod.GET)
    public BaseResponse envListController() {
        log.info("获取环境列表");
        return new SuccessResponse(projectEnvMapper.envList());
    }

    @RequestMapping(value = "/protocol", method = RequestMethod.GET)
    public BaseResponse protocolListController() {
        log.info("获取协议列表");
        return new SuccessResponse(protocolMapper.listProtocol());
    }

    @RequestMapping(value = "/reqeustType", method = RequestMethod.GET)
    public BaseResponse requestTypeListController() {
        log.info("获取请求方式列表");
        return new SuccessResponse(requestTypeMapper.requestTypeList());
    }

    @RequestMapping(value = "/execScript", method = RequestMethod.GET)
    public BaseResponse execScriptController(@RequestParam("scriptId") Integer scriptId, @RequestParam("userId") Integer userId) {
        log.info("执行jmeter脚本id:" + scriptId);
        String md5 = MD5Util.encrypt(UUID.randomUUID().toString());
        String jmeterRedisId = redisService.get("exec_jmeter_id_" + scriptId);
        String jmeterCountRedisId = redisService.get("exec_jmeter_count");
        if (jmeterRedisId == null) {
            redisService.set("exec_jmeter_id_" + scriptId, String.valueOf(scriptId));
        } else {
            return new ErrorResponse(CommonConstant.EXEC_FILE_EXIST);
        }
        if (jmeterCountRedisId == null) {
            redisService.set("exec_jmeter_count", String.valueOf(1));
        } else if (Integer.parseInt(jmeterCountRedisId) >= 2) {
            return new ErrorResponse(CommonConstant.EXEC_COUNT_MAX);
        } else {
            String value = String.valueOf(Integer.parseInt(redisService.get("exec_jmeter_count")) + 1);
            redisService.set("exec_jmeter_count", value);
        }
        try {
            User user = null;
            if (userId != null && userId > 0) {
                user = userMapper.selectByPrimaryKey(userId);
            }
            if (user == null) {
                return new ErrorResponse(CommonConstant.OP_FAILED);
            }
            Script script = null;
            if (scriptId != null && scriptId > 0) {
                script = scriptMapper.selectByPrimaryKey(scriptId);
            }
            if (script == null) {
                return new ErrorResponse(CommonConstant.OP_FAILED);
            }
            RequestType requestType = null;
            Integer requestTypeId = script.getRequestTypeId();
            if (requestTypeId != null && requestTypeId > 0) {
                requestType = requestTypeMapper.selectByPrimaryKey(requestTypeId);
            }
            if (requestType == null) {
                return new ErrorResponse(CommonConstant.OP_FAILED);
            }
            String url = script.getUrl();
            String name = script.getName();
            Integer preNumber = script.getPreNumber();
            Integer preTime = script.getPreTime();
            String ins = script.getIns();
            String method = script.getMethod();
            String assertText = script.getAssertText();
            String timeOut = script.getTimeOut().toString();
            Integer protocolId = script.getProtocolId();
            boolean isOpenServerMonitor = false;
            if (script.getIp() != null && StringUtils.isNoneBlank(script.getIp())){
                isOpenServerMonitor = true;
            }
            String ip = script.getIp();
            String port = null;
            try {
                port = script.getPort().toString();
            }catch (Exception e){
                port = null;
            }

            if (protocolId != null && protocolId > 0) {
                if (protocolId == 1) {
                    log.info("执行http请求");
                    String fileContent = null;
                    try {
                        fileContent = createHttpJmxFile(preNumber, preTime, name, url, requestType.getName(), script.getParams(),
                                script.getTimeOut().toString(), script.getHeader(), script.getCookie(), script.getAssertText(),
                                isOpenServerMonitor, ip, port);
                    }catch (Exception e){
                        log.info(e.getMessage());
                    }
                    String jmxFilePath = jmeterConfig.getJmxFilePath() + md5 + ".jmx";

                    log.info("jmx文件地址:"+jmxFilePath);
                    try {
                        OutputStream os = new FileOutputStream(new File(jmxFilePath));
                        os.write(fileContent.getBytes("utf-8"));
                        os.close();
                    } catch (IOException e) {
                        log.error("http jmx文件写入错误");
                    }
                    File file = new File(jmxFilePath);
                    if (!file.exists()) {
                        log.error("http jmx文件：" + jmxFilePath + "不存在");
                        return new ErrorResponse(CommonConstant.CREATE_JMETER_FILE_FAIL);
                    }
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                String cmd = "%s -n -t %s -l %s -j %s";
                                cmd = String.format(cmd, jmeterConfig.getJmeterBinPath(), jmxFilePath, jmeterConfig.getRunLogPath() + md5 + ".log",
                                        jmeterConfig.getJtlFilePath() + md5 + ".jtl");
                                String[] listCmd = cmd.split(" ");
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
                                }
                                redisService.remove("exec_jmeter_id_" + scriptId);
                                String value = String.valueOf(Integer.parseInt(redisService.get("exec_jmeter_count")) - 1);
                                redisService.set("exec_jmeter_count", value);
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                    History history = new History();
                    history.setCreateTime(new Date());
                    history.setMd5(md5);
                    history.setScriptId(scriptId);
                    history.setUserId(userId);
                    history.setStatus("success");
                    historyMapper.insert(history);
                    return new SuccessResponse(CommonConstant.OP_SUCCESS);
                } else if (protocolId == 2) {
                    log.info("执行dubbo请求");
                    Integer projectId = script.getProjectId();
                    Project project = null;
                    if (projectId != null && projectId > 0) {
                        project = projectMapper.selectByPrimaryKey(projectId);
                    }
                    if (project == null) {
                        return new ErrorResponse(CommonConstant.OP_FAILED);
                    }
                    Integer envId = project.getEnv();
                    ProjectEnv projectEnv = null;
                    if (envId != null && envId > 0) {
                        projectEnv = projectEnvMapper.selectByPrimaryKey(envId);
                    }
                    if (projectEnv == null) {
                        return new ErrorResponse(CommonConstant.OP_FAILED);
                    }
                    String zkAddress = projectEnv.getZk();
                    log.info("开始创建文件");
                    String fileContent = null;
                    try {
                        fileContent = DubboJmeterScript.startSetting() + DubboJmeterScript.crontrolSetting(preNumber, preTime) +
                                DubboJmeterScript.dubboSetting(name, zkAddress, timeOut.toString(), script.getVersion(), ins, method, script.getParamType(), script.getParams()) +
                                DubboJmeterScript.watchReportSetting() + DubboJmeterScript.jhReportSetting() + DubboJmeterScript.responseAssertSetting(assertText) +
                                DubboJmeterScript.rtotReportSetting() + DubboJmeterScript.tpsSetting() +
                                DubboJmeterScript.pmcSetting(isOpenServerMonitor, ip, port) + DubboJmeterScript.endSetting();
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
                        log.error("http jmx文件写入错误");
                    }
                    File file = new File(jmxFilePath);
                    if (!file.exists()) {
                        log.error("dubbo jmx文件：" + jmxFilePath + "不存在");
                        return new ErrorResponse(CommonConstant.CREATE_JMETER_FILE_FAIL);
                    }
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                String cmd = "%s -n -t %s -l %s -j %s";
                                cmd = String.format(cmd, jmeterConfig.getJmeterBinPath(), jmxFilePath, jmeterConfig.getRunLogPath() + md5 + ".log",
                                        jmeterConfig.getJtlFilePath() + md5 + ".jtl");
                                String[] listCmd = cmd.split(" ");
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
                                }
                                redisService.remove("exec_jmeter_id_" + scriptId);
                                String value = String.valueOf(Integer.parseInt(redisService.get("exec_jmeter_count")) - 1);
                                redisService.set("exec_jmeter_count", value);
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                    History history = new History();
                    history.setCreateTime(new Date());
                    history.setMd5(md5);
                    history.setScriptId(scriptId);
                    history.setUserId(userId);
                    history.setStatus("success");
                    historyMapper.insert(history);
                    return new SuccessResponse(CommonConstant.OP_SUCCESS);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            redisService.remove("exec_jmeter_id_" + scriptId);
            String value = String.valueOf(Integer.parseInt(redisService.get("exec_jmeter_count")) - 1);
            redisService.set("exec_jmeter_count", value);
            History history = new History();
            history.setCreateTime(new Date());
            history.setMd5(md5);
            history.setScriptId(scriptId);
            history.setUserId(userId);
            history.setStatus("fail");
            historyMapper.insert(history);
            return new ErrorResponse(2001,"执行脚本失败",new Object[]{e.getMessage()});
        }
        return null;
    }


    @RequestMapping(value = "/testRequest", method = RequestMethod.GET)
    public BaseResponse testRequest(@Valid TestRequest testRequest) {
        log.info("测试接口请求参数：" + testRequest.toString());
        String headers = testRequest.getHeader();
        String cookies = testRequest.getCookie();
        Integer timeOut = testRequest.getTimeOut();
        Integer requestTypeId = testRequest.getRequestTypeId();
        Integer protocolId = testRequest.getProtocolId();
        String url = testRequest.getUrl();
        String params = testRequest.getParams();

        String protocolName = "HTTP";
        String requestTypeName = "GET";

        RequestType requestType = requestTypeMapper.selectByPrimaryKey(requestTypeId);
        if (requestType != null) {
            requestTypeName = requestType.getName();
        }

        Protocol protocol = protocolMapper.selectByPrimaryKey(protocolId);
        if (protocol != null) {
            protocolName = protocol.getName();
        }


        Map<String, String> formMap = new HashMap<>();
        Map<String, String> cookieMap = new HashMap<>();
        Map<String, String> paramMap = new HashMap<>();
        String jsonResult = "";

        if (StringUtils.isNotBlank(headers)) {
            if (headers.contains("headerKey")) {
                JSONArray json = JSON.parseArray(headers);
                for (int i = 0; i < json.size(); i++) {
                    Map<String, String> js = (Map<String, String>) json.get(i);
                    String headerKey = js.get("headerKey");
                    String headerValue = js.get("headerValue");
                    formMap.put(headerKey, headerValue);
                }
            } else {
                headers = null;
            }
        }

        if (StringUtils.isNotBlank(cookies)) {
            if (cookies.contains("cookieKey")) {
                JSONArray json = JSON.parseArray(cookies);
                for (int i = 0; i < json.size(); i++) {
                    Map<String, String> js = (Map<String, String>) json.get(i);
                    String cookieKey = js.get("cookieKey");
                    String cookieValue = js.get("cookieValue");
                    cookieMap.put(cookieKey, cookieValue);
                }
            } else {
                cookies = null;
            }
        }

        if (StringUtils.isNoneBlank(params)) {
            if (params.contains("paramskey")) {
                JSONArray json = JSON.parseArray(params);
                for (int i = 0; i < json.size(); i++) {
                    Map<String, String> js = (Map<String, String>) json.get(i);
                    String paramskey = js.get("paramskey");
                    String paramsvalue = js.get("paramsvalue");
                    paramMap.put(paramskey, paramsvalue);
                }
            }
        }
        String result = "";
        switch (protocolName.toUpperCase()) {
            case "HTTP":
                switch (requestTypeName.toUpperCase()) {
                    case "GET":
                        result = HttpClientUtil.doGet(formMap, cookieMap, timeOut, url, paramMap);
                        break;
                    case "POST":
                        if (MapUtils.isNotEmpty(paramMap) && paramMap != null) {
                            result = HttpClientUtil.doPost(formMap, cookieMap, timeOut, url, paramMap);
                        } else if (StringUtils.isNoneBlank(params)) {
                            result = HttpClientUtil.doPostJson(formMap, cookieMap, timeOut, url, params);
                        } else {
                            result = HttpClientUtil.doPost(formMap, cookieMap, timeOut, url, paramMap);
                        }
                        break;
                    default:
                        result = HttpClientUtil.doGet(formMap, cookieMap, timeOut, url, paramMap);
                        break;

                }
                break;
            case "DUBBO":
                break;

        }
        log.info("测试结果:" + result);
        return new SuccessResponse(result);
    }

    @RequestMapping(value = "/watchLog",method = RequestMethod.GET)
    public BaseResponse watchReportLog(HttpServletRequest request){
        Integer id = Integer.parseInt(request.getParameter("id"));
        String md5 = request.getParameter("md5");
        History history = historyMapper.selectByPrimaryKey(id);
        if (history == null){
            return new ErrorResponse(6003,"查看的日志不存在",null);
        }
        if (!history.getMd5().equals(request.getParameter("md5"))){
            return new ErrorResponse(6003,"查看的日志不存在",null);
        }
        String logContent = readToString(jmeterConfig.getRunLogPath()+md5+".log");
        if (logContent == null){
            return new ErrorResponse(CommonConstant.REPORT_LOG_NOT_EXIST);
        }
        Map<String,String> result = new HashMap<>();
        result.put("log",logContent);
        return new SuccessResponse(result);
    }
    public static String readToString(String fileName) {
        String encoding = "UTF-8";
        File file = new File(fileName);
        Long filelength = file.length();
        byte[] filecontent = new byte[filelength.intValue()];
        try {
            FileInputStream in = new FileInputStream(file);
            in.read(filecontent);
            in.close();
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            return null;
        }
        try {
            return new String(filecontent, encoding);
        } catch (UnsupportedEncodingException e) {
            System.err.println("The OS does not support " + encoding);
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/download")
    public String downloadFile(HttpServletRequest request, HttpServletResponse response) {
        Integer id = Integer.parseInt(request.getParameter("id"));
        String fileName = request.getParameter("md5")+".jmx";
        log.info("下载文件："+fileName);
        History history = historyMapper.selectByPrimaryKey(id);
        if (history == null){
            return "下载文件不存在";
        }
        if (!history.getMd5().equals(request.getParameter("md5"))){
            return "下载文件不存在";
        }
        if (fileName != null) {
            //设置文件路径
            File file = new File(jmeterConfig.getJmxFilePath()+fileName);
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    return "下载成功";
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return "下载失败";
    }

    @RequestMapping(method = RequestMethod.GET,value = "/checkCron")
    public BaseResponse checkCronCrontroller(String cron){
        boolean flag = false;
        CronTriggerImpl trigger = new CronTriggerImpl();
        try {
            trigger.setCronExpression(cron);
            Date date = trigger.computeFirstFireTime(null);
            flag = date != null && date.after(new Date());
        } catch (Exception e) {
            log.error("[TaskUtils.isValidExpression]:failed. throw ex:" , e);
            flag = false;
        }
        return new SuccessResponse(flag);
    }

    @RequestMapping(value = "generateReport",method = RequestMethod.GET)
    public BaseResponse generateReportCrontroller(HttpServletRequest request) {
        log.info("请求生成日志文件");
        Integer id = Integer.parseInt(request.getParameter("id"));
        String md5 = request.getParameter("md5");
        History history = historyMapper.selectByPrimaryKey(id);
        if (history == null){
            return new ErrorResponse(6003,"需生成的日志文件不存在",null);
        }
        if (!history.getMd5().equals(request.getParameter("md5"))){
            return new ErrorResponse(6003,"需生成的日志文件不存在",null);
        }
        String csvContent = "";
        String rtotPath = jmeterConfig.getReportImgPath()+md5+"_rtot.png";
        String tpsPath = jmeterConfig.getReportImgPath()+md5+"_tps.png";
        String csvPath = jmeterConfig.getReportCsvPath()+md5+".csv";
        boolean rtotFlag = timerTaskNoSleep(rtotPath);
        boolean tpsFlag = timerTaskNoSleep(tpsPath);
        boolean csvFlag = timerTaskNoSleep(csvPath);
        if (rtotFlag && tpsFlag && csvFlag){
            Map<String,Object> resultMap = new HashMap<>();
            resultMap.put("tps",jmeterConfig.getStaticServer()+md5+"_tps.png");
            resultMap.put("rtot",jmeterConfig.getStaticServer()+md5+"_rtot.png");
            csvContent = readToString(csvPath);
            resultMap.put("aggregate",csvContent);
            return new SuccessResponse(resultMap);
        }
        try {
            String cmd = "sh %s %s";
            cmd = String.format(cmd, jmeterConfig.getGenerateScriptPath(),md5);
            String[] listCmd = cmd.split(" ");
            Process process = Runtime.getRuntime().exec(listCmd);
            process.waitFor();
            SequenceInputStream sis = new SequenceInputStream(process.getInputStream(), process.getErrorStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(sis, "utf-8"));
            String line;
            while ((line = br.readLine()) != null) {
                log.info(line);
            }
            if (br != null) {
                log.info("命令执行完成");
                rtotFlag = timerTask(rtotPath);
                tpsFlag = timerTask(tpsPath);
                csvFlag = timerTask(csvPath);
                }
                if (!(rtotFlag && tpsFlag && csvFlag)){
                    return new ErrorResponse(CommonConstant.REPORT_GENERATE_FAIL);
                }
                csvContent = readToString(csvPath);
                br.close();
        }catch (Exception e){
            return new ErrorResponse(CommonConstant.REPORT_GENERATE_FAIL);
        }

        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("tps",jmeterConfig.getStaticServer()+md5+"_tps.png");
        resultMap.put("rtot",jmeterConfig.getStaticServer()+md5+"_rtot.png");
        resultMap.put("aggregate",csvContent);
        return new SuccessResponse(resultMap);
    }

    public static boolean timerTask(String csvPath){
        boolean csvFlag = false;
        long oldTime = System.currentTimeMillis();
        long timeOver = 30*1000;
        while (true){
            try {
                File csvFile = new File(csvPath);
                InputStream is = new FileInputStream(csvFile);
                csvFlag = true;
                break;
            }catch (FileNotFoundException e){
                csvFlag = false;
            }
            if (csvFlag){
                break;
            }
            long nowTime =  System.currentTimeMillis();
            if (nowTime - oldTime > timeOver){
                break;
            }
        }
        return csvFlag;
    }

    public static boolean timerTaskNoSleep(String path){
        boolean csvFlag = false;
            try {
                File csvFile = new File(path);
                InputStream is = new FileInputStream(csvFile);
                csvFlag = true;
            }catch (FileNotFoundException e){
                csvFlag = false;
            }
        return csvFlag;
    }

    private static String createHttpJmxFile(Integer preNumber, Integer preTime, String interfaceName, String url,
                                            String requestType, String params, String timeOut, String header, String cookie, String assertText, boolean isOpenServerMonitor, String ip, String port) {
        String fileContent = HttpJmeterScript.headerSetting() + HttpJmeterScript.crontrolSetting(preNumber, preTime) +
                HttpJmeterScript.httpRequestSetting(interfaceName, url, requestType, params, timeOut) + HttpJmeterScript.headerSetting(header) +
                HttpJmeterScript.cookieSetting(cookie, url) + HttpJmeterScript.jhReportSetting() + HttpJmeterScript.responseAssertSetting(assertText) +
                HttpJmeterScript.rtotSetting() + HttpJmeterScript.tpsSetting() + HttpJmeterScript.watchResultSetting() +
                HttpJmeterScript.pmcSetting(isOpenServerMonitor, ip, port) +
                HttpJmeterScript.endSetting();
        return fileContent;
    }

    private static String createDubboJmxFile(Integer preNumber, Integer preTime, String interfaceName, String zkAddress,
                                             String timeOut, String version, String ins, String methodName, String paramType, String params, String assertText, boolean isOpenServerMonitor, String ip, String port) {
        String fileContent = DubboJmeterScript.startSetting() + DubboJmeterScript.crontrolSetting(preNumber, preTime) +
                DubboJmeterScript.dubboSetting(interfaceName, zkAddress, timeOut, version, ins, methodName, paramType, params) + DubboJmeterScript.watchReportSetting() +
                DubboJmeterScript.jhReportSetting() + DubboJmeterScript.responseAssertSetting(assertText) +
                DubboJmeterScript.rtotReportSetting() + DubboJmeterScript.tpsSetting() + DubboJmeterScript.pmcSetting(isOpenServerMonitor, ip, port) + DubboJmeterScript.endSetting();
        return fileContent;
    }

    public static void main(String[] args) {
        boolean resilt = timerTask("/Users/liuzhanhui/Documents/jmeter/project/csv/adc55973b87f237d09a5db2792339d48.csv");
        System.out.println(resilt);
    }
}
