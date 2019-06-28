package com.dubbo.api.common.util;

import org.apache.rocketmq.common.utils.HttpTinyClient;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2019-06-26:14:44
 * Modify date: 2019-06-26:14:44
 */
public class HttpClientUtil {

    public static void main(String[] args) {
        RequestClientInterface http = new RequestClientInterface();
        String url = "https://sso-hotfix.xk12.cn/login?service=https://jiaoshi-hotfix.xk12.cn/";
        Map<String, Object> map = new HashMap<>();
        map.put("username","61104096");
        map.put("password","123456");
        try {
            HttpTinyClient.HttpResult response = null;
            try {
                response = http.doPost(url,map);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            System.out.println("返回code码："+response.code);
            System.out.println("返回内容："+response.content);
            System.out.println("返回内容-----："+response.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
