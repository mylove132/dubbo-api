package com.dubbo.api.util;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import org.apache.rocketmq.common.utils.HttpTinyClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2019-06-26:14:46
 * Modify date: 2019-06-26:14:46
 */
public class RequestClientInterface {
    private CloseableHttpClient httpClient;

    public  RequestClientInterface() {
        // 1 创建HttpClinet，相当于打开浏览器
        this.httpClient = HttpClients.createDefault();
    }

    /**
     * 带参数的post请求
     *
     * @param url
     * @param map
     * @return
     * @throws Exception
     */
    public  HttpTinyClient.HttpResult doPost(String url, Map<String, Object> map) throws Exception {
        // 声明httpPost请求
        HttpPost httpPost = new HttpPost(url);

        // 判断map不为空
        if (map != null) {
            // 声明存放参数的List集合
            List<NameValuePair> params = new ArrayList<NameValuePair>();

            // 遍历map，设置参数到list中
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                params.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
            }

            // 创建form表单对象
            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(params, "utf-8");
            formEntity.setContentType("application/x-www-form-urlencoded");

            // 把表单对象设置到httpPost中
            httpPost.setEntity(formEntity);
        }

        // 使用HttpClient发起请求，返回response
        CloseableHttpResponse response = this.httpClient.execute(httpPost);

        // 解析response封装返回对象httpResult
        HttpTinyClient.HttpResult httpResult = null;
        if (response.getEntity() != null) {
            httpResult = new HttpTinyClient.HttpResult(response.getStatusLine().getStatusCode(),
                    EntityUtils.toString(response.getEntity(), "UTF-8"));
        } else {
            httpResult = new HttpTinyClient.HttpResult(response.getStatusLine().getStatusCode(), "");
        }
        // 返回结果
        return httpResult;
    }

}
