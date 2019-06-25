package com.dubbo.api.task;

import org.springframework.stereotype.Component;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2019-06-25:14:18
 * Modify date: 2019-06-25:14:18
 */
public class ExecJmeterScript {

    public void execJmeterScript(String id){
        System.out.println("执行jmeter脚本"+id);
    }
}
