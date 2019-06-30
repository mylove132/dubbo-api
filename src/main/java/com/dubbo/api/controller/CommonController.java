package com.dubbo.api.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dubbo.api.common.bean.BaseResponse;
import com.dubbo.api.common.bean.SuccessResponse;
import com.dubbo.api.dao.ProjectEnvMapper;
import com.dubbo.api.dao.ProjectTypeMapper;
import com.dubbo.api.dao.ProtocolMapper;
import com.dubbo.api.dao.RequestTypeMapper;
import com.dubbo.api.vo.TestRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/common")
public class CommonController {


    @Autowired
    private ProjectTypeMapper projectTypeMapper;

    @Autowired
    private ProjectEnvMapper projectEnvMapper;

    @Autowired
    private ProtocolMapper protocolMapper;

    @Autowired
    private RequestTypeMapper requestTypeMapper;

    @RequestMapping(value = "/platform",method = RequestMethod.GET)
    public BaseResponse platformListController(){
        log.info("获取平台列表");
        return new SuccessResponse(projectTypeMapper.projectTypeList());
    }

    @RequestMapping(value = "/env",method = RequestMethod.GET)
    public BaseResponse envListController(){
        log.info("获取环境列表");
        return new SuccessResponse(projectEnvMapper.envList());
    }

    @RequestMapping(value = "/protocol",method = RequestMethod.GET)
    public BaseResponse protocolListController(){
        log.info("获取协议列表");
        return new SuccessResponse(protocolMapper.listProtocol());
    }

    @RequestMapping(value = "/reqeustType",method = RequestMethod.GET)
    public BaseResponse requestTypeListController(){
        log.info("获取请求方式列表");
        return new SuccessResponse(requestTypeMapper.requestTypeList());
    }

    @RequestMapping(value = "/testRequest",method = RequestMethod.GET)
    public BaseResponse testRequest(TestRequest testRequest){
        String headers = testRequest.getHeader();
        String cookies = testRequest.getCookie();
        Integer timeOut = testRequest.getTimeOut();
        Integer requestTypeId = testRequest.getRequestTypeId();
        Integer protocolId = testRequest.getProtocolId();
        String url = testRequest.getUrl();

        JSONObject json = JSON.parseObject(headers);

        return null;
    }
}
