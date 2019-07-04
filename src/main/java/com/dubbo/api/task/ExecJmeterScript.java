package com.dubbo.api.task;
import com.dubbo.api.common.util.HttpClientUtil;
import com.dubbo.api.common.util.MD5Util;
import com.dubbo.api.common.util.RequestClientInterface;
import com.dubbo.api.dao.HistoryMapper;
import com.dubbo.api.dao.ScriptMapper;
import com.dubbo.api.vo.History;
import com.dubbo.api.vo.Script;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.utils.HttpTinyClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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


    public void execJmeterScript(String userAndId){
        History history = new History();
        String user = userAndId.split(",")[0];
        String scriptId = userAndId.split(",")[1];
        log.info("执行定时任务：userId:"+user+",scriptId:"+scriptId);
        try {
            String url = "http://127.0.0.1:8901/api/common/execScript?scriptId="+scriptId+"&userId="+user;
            String result = HttpClientUtil.doGet(url);
            log.info("执行定时任务结果："+result);
            String md5 = MD5Util.encrypt(UUID.randomUUID().toString());
            history.setMd5(md5);
            history.setUserId(Integer.parseInt(user));
            history.setScriptId(Integer.parseInt(scriptId));
            history.setCreateTime(new Date());
            history.setStatus("success");
            historyMapper.insert(history);
            return;
        }catch (Exception e){
            String md5 = MD5Util.encrypt(UUID.randomUUID().toString());
            history.setMd5(md5);
            history.setUserId(Integer.parseInt(user));
            history.setScriptId(Integer.parseInt(scriptId));
            history.setCreateTime(new Date());
            history.setStatus("fail");
            historyMapper.insert(history);
            return;
        }

    }

    public static void main(String[] args) {
        String url = "http://127.0.0.1:8901/api/common/execScript?scriptId="+30+"&userId="+1;
        System.out.println(url);
        String result = HttpClientUtil.doGet(url);
        System.out.println(result);
    }
}
