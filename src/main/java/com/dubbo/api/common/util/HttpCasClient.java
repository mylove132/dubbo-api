package com.dubbo.api.common.util;

import com.dubbo.api.vo.request.HttpRequestEntity;
import com.google.common.base.Charsets;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2019-07-29:16:54
 * Modify date: 2019-07-29:16:54
 */
@Slf4j
public class HttpCasClient {
    private static Map<String, String> doCasLoginRequest(String url) {
        Map<String, String> map = new HashMap<>();
        try {
            Document doc = Jsoup.connect(url).get();
            map.put("url", getUrl(url));
            Elements ltNode = doc.select("input[name='lt']");
            map.put("lt", ltNode.val());
            Elements executionNode = doc.select("input[name='execution']");
            map.put("execution", executionNode.val());
            Elements platformTypeNode = doc.select("input[name='platformType']");
            map.put("platformType", platformTypeNode.val());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    public static String getUrl(String url) {
        DefaultHttpClient httpclient = new DefaultHttpClient();
        String location = null;
        int responseCode = 0;
        try {
            final HttpGet request = new HttpGet(url);
            org.apache.http.params.HttpParams params = new BasicHttpParams();
            params.setParameter("http.protocol.handle-redirects", false); // 默认不让重定向
            request.setParams(params);
            HttpResponse response = httpclient.execute(request);
            responseCode = response.getStatusLine().getStatusCode();
            if(responseCode==200){
                return url;
            }else if(responseCode==302){
                org.apache.http.Header locationHeader = response.getFirstHeader("Location");
                if(locationHeader!=null){
                    location = locationHeader.getValue();
                    return location;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static DefaultHttpClient casLogin(HttpRequestEntity httpClientEntity) throws Exception {
        DefaultHttpClient httpClient = null;
        if (!httpClientEntity.getUrl().startsWith("https")) {
            httpClient = new DefaultHttpClient();
        } else {
            httpClient = new SSLClient();
        }
        Map<String, String> result = doCasLoginRequest(httpClientEntity.getUrl());
        httpClient.getParams().setParameter(HttpMethodParams.USER_AGENT, "Mozilla/5.0");
        log.info("请求url:" + result.get("url"));
        HttpPost post = new HttpPost(result.get("url"));
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("username", httpClientEntity.getUsername()));
        nvps.add(new BasicNameValuePair("password", httpClientEntity.getPassword()));
        nvps.add(new BasicNameValuePair("platformType", result.get("platformType")));
        nvps.add(new BasicNameValuePair("lt", result.get("lt")));
        nvps.add(new BasicNameValuePair("execution", result.get("execution")));
        nvps.add(new BasicNameValuePair("_eventId", "submit"));
        log.info("请求数据：" + new UrlEncodedFormEntity(nvps, "UTF-8").toString());
        post.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
        HttpResponse response = httpClient.execute(post);
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            httpClient.getCookieStore().getCookies().forEach(c -> {
                System.out.println(c.getName() + "=>" + c.getValue());
            });
            entity.getContent().close();
        }
        return httpClient;
    }

    // 发送GET请求
    public static String getRequest(DefaultHttpClient client, Map<String, String> headers, Map<String, String> cookies, String path, Integer connTime, List<NameValuePair> parametersBody) throws URISyntaxException {
        if (connTime == null)
            connTime = 1000;
        log.info("请求的url:"+path);
        HttpGet get = new HttpGet(path);
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(connTime).setConnectTimeout(connTime)
                .setRedirectsEnabled(true)
                .build();
        get.setConfig(requestConfig);
        if (MapUtils.isNotEmpty(headers)) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                get.setHeader(entry.getKey(), entry.getValue());
            }
        }
        String cookie = "";
        if (MapUtils.isNotEmpty(cookies)) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                cookie += entry.getKey() + "=" + entry.getValue() + ";";
            }
            get.addHeader("Cookie", cookie);
        }
        try {
            HttpResponse response = client.execute(get);
            int code = response.getStatusLine().getStatusCode();
            if (code >= 400)
                throw new RuntimeException((new StringBuilder()).append("Could not access protected resource. Server returned http code: ").append(code).toString());
            return EntityUtils.toString(response.getEntity());
        } catch (ClientProtocolException e) {
            throw new RuntimeException("postRequest -- Client protocol exception!", e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            get.releaseConnection();
        }
    }

    // 发送POST请求（普通表单形式）
    public static String postForm(DefaultHttpClient client, String path, List<NameValuePair> parametersBody, Map<String, String> headers, Map<String, String> cookies, Integer connTime) {
        if (connTime == null)
            connTime = 1000;
        HttpEntity entity = new UrlEncodedFormEntity(parametersBody, Charsets.UTF_8);
        return postRequest(client, headers, cookies, connTime, path, "application/x-www-form-urlencoded", entity);
    }

    // 发送POST请求（JSON形式）
    public static String postJSON(DefaultHttpClient client, String path, String json, Map<String, String> headers, Map<String, String> cookies, Integer connTime) {
        if (connTime == null)
            connTime = 1000;
        StringEntity entity = new StringEntity(json, Charsets.UTF_8);
        entity.setContentType("application/json");
        return postRequest(client, headers, cookies, connTime, path, "application/json", entity);
    }

    // 发送POST请求
    public static String postRequest(DefaultHttpClient client, Map<String, String> headers, Map<String, String> cookies, Integer connTime, String path, String mediaType, HttpEntity entity) {
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
        if (MapUtils.isNotEmpty(headers)) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                post.setHeader(entry.getKey(), entry.getValue());
            }
        }
        String cookie = "";
        if (MapUtils.isNotEmpty(cookies)) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                cookie += entry.getKey() + "=" + entry.getValue() + ";";
            }
            post.addHeader("Cookie", cookie);
        }
        post.setEntity(entity);
        try {
            HttpResponse response = client.execute(post);
            int code = response.getStatusLine().getStatusCode();
            if (code >= 400)
                throw new RuntimeException(EntityUtils.toString(response.getEntity()));
            return EntityUtils.toString(response.getEntity());
        } catch (ClientProtocolException e) {
            throw new RuntimeException(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            post.releaseConnection();
        }
    }
}
