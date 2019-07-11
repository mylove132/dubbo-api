package com.dubbo.api.common.util;

import lombok.extern.slf4j.Slf4j;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2019-07-09:16:47
 * Modify date: 2019-07-09:16:47
 */
@Slf4j
public class XmlUtil {

    private static SAXReader reader = new SAXReader();
    public static void modifyNodeXml(String path, Map<String,String> nodeMap){
        try {
            Document document = reader.read(new FileInputStream(new File(path)));
            Element element = document.getRootElement();
            List<Element> targets = element.elements("property");
            for (Element ele:targets){
                for (Map.Entry<String,String> entry:nodeMap.entrySet()){
                    if (ele.attribute("name").getValue().equals(entry.getKey())){
                        ele.attribute("value").setValue(entry.getValue());
                    }
                }

            }
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("UTF-8");
            XMLWriter writer = new XMLWriter(new FileWriter(path),format);
            writer.write(document);
            writer.close();

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            log.error(path+"文件未找到");
            e.printStackTrace();
        } catch (IOException e) {
            log.error(path+"文件写入异常");
            e.printStackTrace();
        }
    }

    public static void modifySelectNodeXml(String path, Map<String,String> nodeMap){
        try {
            Document document = reader.read(new FileInputStream(new File(path)));
            Element element = document.getRootElement();
            List<Element> targets = element.elements("target");
            for (Element ele:targets){
                if (ele.attribute("name").getValue().equals("test")){
                    Element testplansEle = ele.element("jmeter").element("testplans");
                    testplansEle.attribute("dir").setValue(nodeMap.get("dir"));
                    testplansEle.attribute("includes").setValue(nodeMap.get("includes"));
                }
            }
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("UTF-8");
            XMLWriter writer = new XMLWriter(new FileWriter(path),format);
            writer.write(document);
            writer.close();

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            log.error(path+"文件未找到");
            e.printStackTrace();
        } catch (IOException e) {
            log.error(path+"文件写入异常");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Map<String,String> result = new HashMap<>();
        result.put("jmeter.home","/Users/liuzhanhui/Documents/jmeter/apache-jmeter-5.1.1");
        result.put("report.title","测试说明");
        result.put("jmeter.result.jtl.dir","/Users/liuzhanhui/Documents/jmeter/project/jtl/");
        result.put("jmeter.result.html.dir","/Users/liuzhanhui/Documents/jmeter/project/html");
        result.put("ReportName","d8f458879c40fdca55b8a742e80ea7a7");
        XmlUtil.modifyNodeXml("/Users/liuzhanhui/Documents/jmeter/project/build/build.xml",result);
        Map<String,String> selectMap = new HashMap<>();
        selectMap.put("dir","/Users/liuzhanhui/Documents/jmeter/project/jmx/");
        selectMap.put("includes","d8f458879c40fdca55b8a742e80ea7a7"+".jmx");
        XmlUtil.modifySelectNodeXml("/Users/liuzhanhui/Documents/jmeter/project/build/build.xml",selectMap);
    }
}
