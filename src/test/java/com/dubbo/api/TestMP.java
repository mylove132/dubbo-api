package com.dubbo.api;

import com.dubbo.api.common.util.HttpClientUtil;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2019-06-25:10:36
 * Modify date: 2019-06-25:10:36
 */
public class TestMP {

    public static void main1(String[] args) {
        int[] mp = {1,5,2,3,7,9,21,18};
        int tmp;

        //循环数组的比对次数
        for (int i= 0;i < mp.length-1; i++){
            //循环每个数需要比对的次数
            for (int k = 0;k < mp.length - i -1;k++){
                if (mp[k] > mp[k+1]){
                    tmp = mp[k];
                    mp[k] = mp[k+1];
                    mp[k+1] = tmp;
                }
            }
        }
        for (int z = 0;z<mp.length;z++){
            System.out.print(mp[z]+" ");
        }
    }

        public static void main(String[] args) {
            HashMap<String,String> formMap = new HashMap<>();
            formMap.put("requestid","2132133121");
            formMap.put("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1aWQiOiIxMzA3MjciLCJzeXN0ZW1JZCI6IjgxOTUxMDk2NjA5Iiwib3JnSWQiOiI4MCIsInRpbWVzdGFtcCI6IjE1NjA5MzAyMDY4NDUifQ.bNoV9ARuwTsHr9Z7uQlE6-7CN7XIfwwoM3LaEBhFMtQ");
            formMap.put("Content-Type","application/json");
            String url = "http://stupad-hotfix.xk12.cn/api/pad/xiaoyun/knowledge";
            String params = "{\n" +
                    "    \"ktype\": 1,\n" +
                    "    \"klevel\": 2\n" +
                    "}";
            String a = HttpClientUtil.doPostJson(formMap, null, 2000, url, params);
            System.out.println(a);
        }
}
