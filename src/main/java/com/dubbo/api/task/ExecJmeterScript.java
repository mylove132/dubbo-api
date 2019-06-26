package com.dubbo.api.task;
import com.dubbo.api.util.RequestClientInterface;
import org.apache.rocketmq.common.utils.HttpTinyClient;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2019-06-25:14:18
 * Modify date: 2019-06-25:14:18
 */
public class ExecJmeterScript {

    public void execJmeterScript(String userAndId){
        String user = userAndId.split(",")[0];
        String id = userAndId.split(",")[1];
        String url = "http://127.0.0.1:8000/api/exec/script";
        RequestClientInterface http = new RequestClientInterface();
        Map<String, Object> map = new HashMap<>();
        map.put("user",user);
        map.put("id",id);
        try {
            HttpTinyClient.HttpResult response = http.doPost(url,map);
            System.out.println("返回code码："+response.code);
            System.out.println("返回内容："+response.content);
            System.out.println("返回内容-----："+response.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
