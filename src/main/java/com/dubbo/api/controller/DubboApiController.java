package com.dubbo.api.controller;

import com.alibaba.dubbo.rpc.service.GenericService;
import com.dubbo.api.model.DubboEntity;
import com.dubbo.api.model.RequestTypeArgments;
import com.dubbo.api.response.CommonResponse;
import com.dubbo.api.response.ErrorCode;
import com.dubbo.api.response.Response;
import com.dubbo.api.util.ClassUtils;
import com.dubbo.api.util.ZkServiceUtil;
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
    public Response testDubboInterface(@RequestBody DubboEntity entity) {
        if (entity.getInterfaceName() == null || entity.getInterfaceName().equals("")) {
            return CommonResponse.makeRsp(ErrorCode.REQUEST_PARAM_NULL.setMsg("接口不能为空"));
        }
        if (entity.getMethodName() == null || entity.getMethodName().equals("")) {
            return CommonResponse.makeRsp(ErrorCode.REQUEST_PARAM_NULL.setMsg("方法不能为空"));
        }
        List<RequestTypeArgments> requestTypeArgments = entity.getRequestParamTypeArgs();
        List<String> paramTypeList = new ArrayList<>();
        List<Object> paramValueList = new ArrayList<>();
        for (RequestTypeArgments typeArgments : requestTypeArgments) {
            if (typeArgments.getParamType() == null || typeArgments.getParamType().equals("")) {
                return CommonResponse.makeRsp(ErrorCode.REQUEST_PARAM_NULL.setMsg("参数类型不能为空"));
            }
            if (typeArgments.getParamValue() == null || typeArgments.getParamValue().equals("")) {
                return CommonResponse.makeRsp(ErrorCode.REQUEST_PARAM_NULL.setMsg("参数值不能为空"));
            }
            ClassUtils.parseParameter(paramTypeList, paramValueList, typeArgments);
        }
        if (entity.getAddress() == null || entity.getAddress().equals("")) {
            return CommonResponse.makeRsp(ErrorCode.REQUEST_PARAM_NULL.setMsg("协议地址不能为空"));
        }
        if (entity.getProtocol() == null || entity.getProtocol().equals("")) {
            return CommonResponse.makeRsp(ErrorCode.REQUEST_PARAM_NULL.setMsg("协议不能为空"));
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
        return CommonResponse.makeOKRsp(result);
    }

}
