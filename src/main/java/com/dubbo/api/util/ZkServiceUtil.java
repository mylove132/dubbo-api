package com.dubbo.api.util;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.rpc.service.GenericService;
import com.dubbo.api.model.DubboEntity;
import com.dubbo.api.model.RequestTypeArgments;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2019-01-02:10:56
 * Modify date: 2019-01-02:10:56
 */
public class ZkServiceUtil {

    public GenericService getGenericService(DubboEntity entity) {
        String address = "";
        if(entity.getAddress().equals("1")){
            address = "10.10.6.3:2181";
        }else if(entity.getAddress().equals("2")){
            address = "172.18.4.48:2181";
        }else if(entity.getAddress().equals("3")){
            address = "10.10.1.7:2181";
        }else {
            address = "10.10.6.3:2181";
        }
        RegistryConfig registry = new RegistryConfig();
        registry.setAddress(address);
        registry.setProtocol(entity.getProtocol());
        registry.setGroup(entity.getGroup());
        ReferenceConfig<GenericService> reference = new ReferenceConfig<GenericService>();
        reference.setApplication(new ApplicationConfig("qa-dubbo-jmeter-web"));
        reference.setInterface(entity.getInterfaceName());
        reference.setProtocol("dubbo");
        reference.setTimeout(entity.getTimeOut());
        reference.setVersion(entity.getVersion());
        reference.setGroup(entity.getGroup());
        reference.setRegistry(registry);
        reference.setGeneric(true);
        GenericService genericService = reference.get();
        return genericService;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        ZkServiceUtil util = new ZkServiceUtil();
        DubboEntity dubboEntity = new DubboEntity();
        dubboEntity.setGroup(null);
        dubboEntity.setVersion("3.0.0");
        dubboEntity.setTimeOut(200000);
        dubboEntity.setAddress("");
        dubboEntity.setProtocol("zookeeper");
        dubboEntity.setInterfaceName("com.noriental.lessonsvr.rservice.ResPackageService");
        //{"catalogGroupId":45974,"catalogId":2693260,"chapterFullName":"1.1","chapterId":0,"chapterList":[1],"creatorId":61951054990,"creatorName":"李三","desc":"0830 作业 新编辑看看多少道题集","directoryId":0,"packageResource":[{"allSingle":0,"duration":0,"extId":0,"md5":"rw_yynNFSeVTW.ppt","questionCount":21,"resourceId":187589,"resourceName":"0830 作业 新编辑看看多少道题集","resourceType":2,"subQuestionCount":39}],"publishType":1,"subjectId":5,"year":2017}
        List<RequestTypeArgments> requestTypeArgments = new ArrayList<>();
        RequestTypeArgments typeArgments = new RequestTypeArgments("com.noriental.lessonsvr.entity.request.ResPackageQueryRequest","{\"chapterId\":12989,\"chapterLevel\":1,\"directoryId\":16,\"moduleId\":12988,\"pageBounds\":{\"containsTotalCount\":true,\"limit\":1,\"offset\":0,\"orders\":[],\"page\":1},\"publishTypes\":[1],\"teacherSystemId\":6107868,\"topicId\":12989,\"unitId\":0}");
        List<String> paramTypeList = new ArrayList<>();
        List<Object> paramValueList = new ArrayList<>();
        ClassUtils.parseParameter(paramTypeList, paramValueList, typeArgments);
        System.out.println("测试数据"+paramTypeList);
        System.out.println("测试数据"+paramValueList);
        System.out.println(dubboEntity.getMethodName());
        dubboEntity.setRequestParamTypeArgs(requestTypeArgments);
        try {
            Object result = util.getGenericService(dubboEntity).$invoke("findResPackage",
                    paramTypeList.toArray(new String[paramTypeList.size()]),
                    paramValueList.toArray(new Object[paramValueList.size()]));
            System.out.println("结果："+result);
        }catch (Exception ex){
            ex.printStackTrace();

        }

//        PageBounds pageBounds = new PageBounds(1,10);
////        System.out.println(pageBounds);
////        //String json = JsonUtils.toJson(pageBounds);
//        String json = JSONObject.toJSONString(pageBounds);
//        System.out.println(json);
//        Object page = JSONObject.parseObject("{\"containsTotalCount\":true,\"limit\":10,\"offset\":0,\"orders\":[],\"page\":1}");
//        System.out.println(page);
    }



}
