package com.dubbo.api.common.util;


import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
/**
 * Created by Administrator on 2018/4/27.
 */
@Slf4j
public class HttpClientUtil {

//    public static String doGet(Map<String,String> headers, Map<String,String> cookies, Integer connTime, String url, Map<String, String> param) {
//        if (connTime == null){
//            connTime = 1000;
//        }
//        // 创建Httpclient对象
//        CloseableHttpClient httpclient = HttpClients.createDefault();
//        String cookValues = "";
//        if (cookies != null){
//            for (Map.Entry<String,String> entry : cookies.entrySet()) {
//                String cookV = entry.getKey()+"="+entry.getValue()+";";
//                cookValues = cookValues +cookV;
//            }
//        }
//        HttpGet HttpGet = new HttpGet(url);
//        // 设置请求和传输超时时间
//        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(connTime).setConnectTimeout(connTime)
//                .setRedirectsEnabled(false) // 不自动重定向
//                .build();
//        HttpGet.setConfig(requestConfig);
//        HttpGet.setHeader("Cookie",cookValues);
//        if (headers != null) {
//            for (Map.Entry<String, String> hs : headers.entrySet()) {
//                HttpGet.setHeader(hs.getKey(), hs.getValue());
//            }
//        }
//
//
//        String resultString = "";
//        CloseableHttpResponse response = null;
//        try {
//            // 创建uri
//            URIBuilder builder = new URIBuilder(url);
//            if (param != null) {
//                for (String key : param.keySet()) {
//                    builder.addParameter(key, param.get(key));
//                }
//            }
//            URI uri = builder.build();
//
//            // 创建http GET请求
//            HttpGet httpGet = new HttpGet(uri);
//
//            // 执行请求
//            response = httpclient.execute(HttpGet);
//            resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
//            if (response.getStatusLine().getStatusCode() == 302){
//                resultString = "返回code:"+response.getStatusLine().getStatusCode();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            resultString = e.getMessage();
//        } finally {
//            try {
//                if (response != null) {
//                    response.close();
//                }
//                httpclient.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//                resultString = e.getMessage();
//            }
//        }
//        log.info("http get 请求结果："+resultString);
//        return resultString;
//    }
//
//    public static String doGet(String url) {
//        return doGet(null,null,5000,url, null);
//    }
//
//    public static String doGet(Map<String,String> headers,String url) {
//        return doGet(headers,null,5000,url, null);
//    }
//
//    public static String doGetCookies(Map<String,String> cookies,String url) {
//        return doGet(null,cookies,5000,url, null);
//    }
//
//    public static String doGet(Map<String,String> headers,Map<String,String> cookies,String url) {
//        return doGet(headers,cookies,5000,url, null);
//    }
//
//
//    public static String doPost(Map<String,String> headers, Map<String,String> cookies, Integer connTime,String url, Map<String, String> param) {
//        if (connTime == null){
//            connTime = 1000;
//        }
//        // 创建Httpclient对象
//        String cookValues = "";
//        if (cookies != null){
//            for (Map.Entry<String,String> entry : cookies.entrySet()) {
//                String cookV = entry.getKey()+"="+entry.getValue()+";";
//                cookValues = cookValues +cookV;
//            }
//        }
//        // 设置请求和传输超时时间
//        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(connTime).setConnectTimeout(connTime)
//                .setRedirectsEnabled(false) // 不自动重定向
//                .build();
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        CloseableHttpResponse response = null;
//        String resultString = "";
//        try {
//            // 创建Http Post请求
//            HttpPost httpPost = new HttpPost(url);
//            httpPost.setHeader("Cookie",cookValues);
//            httpPost.setConfig(requestConfig);
//            if (headers != null) {
//                for (Map.Entry<String, String> hs : headers.entrySet()) {
//                    httpPost.setHeader(hs.getKey(), hs.getValue());
//                }
//            }
//            // 创建参数列表
//            if (param != null) {
//                List<NameValuePair> paramList = new ArrayList<>();
//                for (String key : param.keySet()) {
//                    paramList.add(new BasicNameValuePair(key, param.get(key)));
//                }
//                // 模拟表单
//                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList,"utf-8");
//                httpPost.setEntity(entity);
//            }
//            // 执行http请求
//            response = httpClient.execute(httpPost);
//            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
//            if (response.getStatusLine().getStatusCode() == 302){
//                resultString = "返回code:"+response.getStatusLine().getStatusCode();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                response.close();
//            } catch (IOException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//                resultString = e.getMessage();
//            }
//        }
//        log.info("http post 请求结果："+resultString);
//        return resultString;
//    }
//
//    public static String doPost(String url) {
//        return doPost(null,null,5000,url, null);
//    }
//
//    public static String doPost(Map<String,String> headers, String url) {
//        return doPost(headers,null,5000,url, null);
//    }
//
//    public static String doPostCookies(Map<String,String> cookies, String url) {
//        return doPost(null,cookies,5000,url, null);
//    }
//
//    public static String doPost(Map<String,String> headers,Map<String,String> cookies, String url) {
//        return doPost(headers,cookies,5000,url, null);
//    }
//
//    public static String doPostJson(Map<String,String> headers,Map<String,String> cookies,Integer connTime,String url, String json) {
//        if (connTime == null){
//            connTime = 1000;
//        }
//        String cookValues = "";
//        if (cookies != null){
//            for (Map.Entry<String,String> entry : cookies.entrySet()) {
//                String cookV = entry.getKey()+"="+entry.getValue()+";";
//                cookValues = cookValues +cookV;
//            }
//        }
//        // 设置请求和传输超时时间
//        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(connTime).setConnectTimeout(connTime)
//                .setRedirectsEnabled(false) // 不自动重定向
//                .build();
//        // 创建Httpclient对象
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        CloseableHttpResponse response = null;
//        String resultString = "";
//        try {
//            // 创建Http Post请求
//            HttpPost httpPost = new HttpPost(url);
//            httpPost.setHeader("Cookie",cookValues);
//            httpPost.setConfig(requestConfig);
//            if (headers != null) {
//                for (Map.Entry<String, String> hs : headers.entrySet()) {
//                    httpPost.setHeader(hs.getKey(), hs.getValue());
//                }
//            }
//            // 创建请求内容
//            StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
//            log.info("请求实体："+json);
//            httpPost.setEntity(entity);
//            // 执行http请求
//            response = httpClient.execute(httpPost);
//            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
//            if (response.getStatusLine().getStatusCode() != 200){
//                resultString = "返回code:"+response.getStatusLine().getStatusCode();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            resultString = e.getMessage();
//        } finally {
//            try {
//                response.close();
//            } catch (IOException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//        }
//        log.info("http post json请求结果："+resultString);
//        return resultString;
//    }
//
//    public static String doPostJson(Map<String,String> headers,String url,String json){
//        return doPostJson(headers,null,5000,url,json);
//    }
//
//    public static String doPostJsonCookies(Map<String,String> cookies,String url,String json){
//        return doPostJson(null,cookies,5000,url,json);
//    }
}
