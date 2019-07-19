package com.dubbo.api.controller;

import com.alibaba.dubbo.rpc.service.GenericService;
import com.dubbo.api.common.bean.BaseResponse;
import com.dubbo.api.common.bean.ErrorResponse;
import com.dubbo.api.common.bean.SuccessResponse;
import com.dubbo.api.common.constant.CommonConstant;
import com.dubbo.api.common.util.ClassUtils;
import com.dubbo.api.common.util.ZkServiceUtil;
import com.dubbo.api.dao.ProjectEnvMapper;
import com.dubbo.api.vo.DubboEntity;
import com.dubbo.api.vo.ProjectEnv;
import com.dubbo.api.vo.RequestTypeArgments;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2019-06-13:09:53
 * Modify date: 2019-06-13:09:53
 */
@Slf4j
@RestController
@RequestMapping("/api")
public class DubboApiController {

    @Autowired
    private ProjectEnvMapper projectEnvMapper;

    ZkServiceUtil zkServiceUtil = new ZkServiceUtil();


    @RequestMapping(value = "/dubboTest", method = RequestMethod.POST)
    public BaseResponse testDubboInterface(@RequestBody DubboEntity entity) {
        log.info("dubbo接口请求参数:"+entity.toString());
        if (entity.getProtocol() == null){
            entity.setProtocol("zookeeper");
        }
        if (entity.getInterfaceName() == null || entity.getInterfaceName().equals("")) {
            return new ErrorResponse(CommonConstant.PARAM_NULL_FAIL);
        }
        if (entity.getMethodName() == null || entity.getMethodName().equals("")) {
            return new ErrorResponse(CommonConstant.PARAM_NULL_FAIL);
        }
        List<RequestTypeArgments> requestTypeArgments = entity.getRequestParamTypeArgs();
        List<String> paramTypeList = new ArrayList<>();
        List<Object> paramValueList = new ArrayList<>();
        for (RequestTypeArgments typeArgments : requestTypeArgments) {
            if (typeArgments.getParamType() == null || typeArgments.getParamType().equals("")) {
                return new ErrorResponse(CommonConstant.PARAM_NULL_FAIL);
            }
            if (typeArgments.getParamValue() == null || typeArgments.getParamValue().equals("")) {
                return new ErrorResponse(CommonConstant.PARAM_NULL_FAIL);
            }
            ClassUtils.parseParameter(paramTypeList, paramValueList, typeArgments);
        }
        if (entity.getAddress() == null || entity.getAddress().equals("")) {
            return new ErrorResponse(CommonConstant.PARAM_NULL_FAIL);
        }
        if (entity.getProtocol() == null || entity.getProtocol().equals("")) {
            return new ErrorResponse(CommonConstant.PARAM_NULL_FAIL);
        }
        if (entity.getTimeOut() == 0||entity.getTimeOut() == null) {
            entity.setTimeOut(5000);
        }
        if (entity.getGroup().equals("") || entity.getGroup() == null) {
            entity.setGroup(null);
        }
        if (entity.getVersion() == null || entity.getVersion().equals("")) {
            entity.setVersion("3.0.0");
        }
        String address = "";
        if (entity.getAddress() == null|| entity.getAddress().equals("0")){
            entity.setAddress("1");
        }
        log.info("address id:"+Integer.parseInt(entity.getAddress()));
        ProjectEnv projectEnv = projectEnvMapper.selectByPrimaryKey(Integer.parseInt(entity.getAddress()));
        address = projectEnv.getZk();
        entity.setAddress(address);
        Object result = null;
        try {
            GenericService service = zkServiceUtil.getGenericService(entity);
            result = service.$invoke(entity.getMethodName(),
                    paramTypeList.toArray(new String[paramTypeList.size()]),
                    paramValueList.toArray(new Object[paramValueList.size()]));
        }catch (Exception e){
            return new SuccessResponse(e.getMessage());
        }

        return new SuccessResponse(result);
    }

}
