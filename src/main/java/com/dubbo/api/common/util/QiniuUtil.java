package com.dubbo.api.common.util;

import java.io.FileInputStream;

import com.dubbo.api.config.JmeterConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

@Slf4j
@Component
public class QiniuUtil {

    @Autowired
    private JmeterConfig jmeterConfig;
    /**
     * 将图片上传到七牛云
     *      * @Title: uploadImg
     *      * @author tianshuhao
     *      * @param @param file
     *      * @param @param key 保存在空间中的名字，如果为空会使用文件的hash值为文件名
     *      * @param @return    参数
     *      * @return String    返回类型
     *      * @throws
     *      
     */
    public String uploadImg(FileInputStream file, String key) {
//构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone1());
//...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
//...生成上传凭证，然后准备上传
        String bucket = jmeterConfig.getBUCKET();
        String path = jmeterConfig.getPATH();
        String accessKey = jmeterConfig.getACCESSKEY();
        String secretKey = jmeterConfig.getSECRETKEY();

        //默认不指定key的情况下，以文件内容的hash值作为文件名
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);

        try {
            Response response = uploadManager.put(file, key, upToken, null, null);
            //解析上传成功的结果
            DefaultPutRet putRet = JSON.parseObject(response.bodyString(), DefaultPutRet.class);
//                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
//                System.out.println(putRet.key);
//                System.out.println(putRet.hash);
            String return_path = path + "/" + putRet.key;
            log.info("保存地址={}", return_path);
            return return_path;
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
        return "";
    }
}
