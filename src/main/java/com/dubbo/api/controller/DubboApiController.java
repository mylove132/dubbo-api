package com.dubbo.api.controller;

import com.alibaba.dubbo.rpc.service.GenericService;
import com.dubbo.api.common.bean.BaseResponse;
import com.dubbo.api.common.bean.ErrorResponse;
import com.dubbo.api.common.bean.SuccessResponse;
import com.dubbo.api.common.util.ClassUtils;
import com.dubbo.api.common.util.ZkServiceUtil;
import com.dubbo.api.vo.DubboEntity;
import com.dubbo.api.vo.RequestTypeArgments;
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
@RestController
@RequestMapping("/api")
public class DubboApiController {

    ZkServiceUtil zkServiceUtil = new ZkServiceUtil();
    @RequestMapping(value = "/dubboTest", method = RequestMethod.POST)
    public BaseResponse testDubboInterface(@RequestBody DubboEntity entity) {
        if (entity.getInterfaceName() == null || entity.getInterfaceName().equals("")) {
            return new ErrorResponse(10001,"接口不能为空",null);
        }
        if (entity.getMethodName() == null || entity.getMethodName().equals("")) {
            return new ErrorResponse(10002,"方法不能为空",null);
        }
        List<RequestTypeArgments> requestTypeArgments = entity.getRequestParamTypeArgs();
        List<String> paramTypeList = new ArrayList<>();
        List<Object> paramValueList = new ArrayList<>();
        for (RequestTypeArgments typeArgments : requestTypeArgments) {
            if (typeArgments.getParamType() == null || typeArgments.getParamType().equals("")) {
                return new ErrorResponse(10003,"参数类型不能为空",null);
            }
            if (typeArgments.getParamValue() == null || typeArgments.getParamValue().equals("")) {
                return new ErrorResponse(10004,"参数值不能为空",null);
            }
            ClassUtils.parseParameter(paramTypeList, paramValueList, typeArgments);
        }
        if (entity.getAddress() == null || entity.getAddress().equals("")) {
            return new ErrorResponse(10005,"协议地址不能为空",null);
        }
        if (entity.getProtocol() == null || entity.getProtocol().equals("")) {
            return new ErrorResponse(10005,"协议不能为空",null);
        }
        if (entity.getTimeOut() == 0) {
            entity.setTimeOut(5000);
        }
        if (entity.getGroup().equals("") || entity.getGroup() == null) {
            entity.setGroup(null);
        }
        if (entity.getVersion() == null || entity.getVersion().equals("")) {
            entity.setVersion("3.0.0");
        }

        GenericService service = zkServiceUtil.getGenericService(entity);
        System.out.println(paramValueList.get(0));
        Object result = service.$invoke(entity.getMethodName(),
                paramTypeList.toArray(new String[paramTypeList.size()]),
                paramValueList.toArray(new Object[paramValueList.size()]));
        return new SuccessResponse(result);
    }

}
