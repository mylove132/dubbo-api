package com.dubbo.api.config;

import com.google.common.base.Charsets;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2019-07-26:16:52
 * Modify date: 2019-07-26:16:52
 */
@Slf4j
public class HttpClientUtil {
    // 发送GET请求
    public static String getRequest(Map<String,String> headers,Map<String,String> cookies, String path,Integer connTime, List<NameValuePair> parametersBody) throws URISyntaxException {
        if (connTime == null)
            connTime = 1000;
        URIBuilder uriBuilder = new URIBuilder(path);
        uriBuilder.setParameters(parametersBody);
        HttpGet get = new HttpGet(uriBuilder.build());
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(connTime).setConnectTimeout(connTime)
                .setRedirectsEnabled(true)
                .build();
        get.setConfig(requestConfig);
        if (MapUtils.isNotEmpty(headers)){
            for (Map.Entry<String,String> entry : headers.entrySet()){
                get.setHeader(entry.getKey(),entry.getValue());
            }
        }
        String cookie = "";
        if (MapUtils.isNotEmpty(cookies)){
            for (Map.Entry<String,String> entry : headers.entrySet()){
                cookie += entry.getKey()+"="+entry.getValue()+";";
            }
            get.addHeader("Cookie",cookie);
        }
        HttpClient client = HttpClientBuilder.create().build();
        try {
            HttpResponse response = client.execute(get);
            int code = response.getStatusLine().getStatusCode();
            if (code >= 400)
                throw new RuntimeException((new StringBuilder()).append("Could not access protected resource. Server returned http code: ").append(code).toString());
            return EntityUtils.toString(response.getEntity());
        }
        catch (ClientProtocolException e) {
            throw new RuntimeException("postRequest -- Client protocol exception!", e);
        }
        catch (IOException e) {
            throw new RuntimeException("postRequest -- IO error!", e);
        }
        finally {
            get.releaseConnection();
        }
    }

    // 发送POST请求（普通表单形式）
    public static String postForm(String path, List<NameValuePair> parametersBody,Map<String,String> headers,Map<String,String> cookies,Integer connTime) {
        if (connTime == null)
            connTime = 1000;
        HttpEntity entity = new UrlEncodedFormEntity(parametersBody, Charsets.UTF_8);
        return postRequest(headers,cookies,connTime,path, "application/x-www-form-urlencoded", entity);
    }

    // 发送POST请求（JSON形式）
    public static String postJSON(String path, String json,Map<String,String> headers,Map<String,String> cookies,Integer connTime)  {
        if (connTime == null)
            connTime = 1000;
        StringEntity entity = new StringEntity(json, Charsets.UTF_8);
        entity.setContentType("application/json");
        return postRequest(headers,cookies,connTime,path, "application/json", entity);
    }

    // 发送POST请求
    public static String postRequest(Map<String,String> headers,Map<String,String> cookies,Integer connTime,String path, String mediaType, HttpEntity entity) {
        log.info("[postRequest] resourceUrl: {}", path);
        if (connTime == null)
            connTime = 1000;
        HttpPost post = new HttpPost(path);
        post.addHeader("Content-Type", mediaType);
        post.addHeader("Accept", "application/json");
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(connTime).setConnectTimeout(connTime)
                .setRedirectsEnabled(true)
                .build();
        post.setConfig(requestConfig);
        if (MapUtils.isNotEmpty(headers)){
            for (Map.Entry<String,String> entry : headers.entrySet()){
                post.setHeader(entry.getKey(),entry.getValue());
            }
        }
        String cookie = "";
        if (MapUtils.isNotEmpty(cookies)){
            for (Map.Entry<String,String> entry : headers.entrySet()){
                cookie += entry.getKey()+"="+entry.getValue()+";";
            }
            post.addHeader("Cookie",cookie);
        }
        post.setEntity(entity);
        try {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpResponse response = client.execute(post);
            int code = response.getStatusLine().getStatusCode();
            if (code >= 400)
                throw new RuntimeException(EntityUtils.toString(response.getEntity()));
            return EntityUtils.toString(response.getEntity());
        }
        catch (ClientProtocolException e) {
            throw new RuntimeException(e.getMessage());
        }
        catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        finally {
            post.releaseConnection();
        }
    }
}