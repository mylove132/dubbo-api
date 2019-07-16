package com.dubbo.api;

import com.dubbo.api.common.util.HttpClientUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.SequenceInputStream;
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

    public static void main(String[] args) throws IOException, InterruptedException {
        String cmd = "/xdfapp/apps/jmeter/server/apache-jmeter-5.1.1/bin/jmeter -n -t /Users/liuzhanhui/Documents/jmeter/project/jmx/6760d8253f837deb50969092bb09f877.jmx -l /Users/liuzhanhui/Documents/jmeter/project/jtl/6760d8253f837deb50969092bb09f877.jtl -e -o /Users/liuzhanhui/Documents/jmeter/project/html/6760d8253f837deb50969092bb09f877";
        BufferedReader br = null;
        Process p = Runtime.getRuntime().exec(cmd);
        br = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        if (br != null) {
            br.close();
        }
    }
}
