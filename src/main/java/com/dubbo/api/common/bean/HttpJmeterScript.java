package com.dubbo.api.common.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2019-07-02:17:55
 * Modify date: 2019-07-02:17:55
 */
@Slf4j
public class HttpJmeterScript {
    public static String headerSetting() {
        String text = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<jmeterTestPlan version=\"1.2\" properties=\"5.0\" jmeter=\"5.1.1 r1855137\">\n" +
                "  <hashTree>\n" +
                "    <TestPlan guiclass=\"TestPlanGui\" testclass=\"TestPlan\" testname=\"Test Plan\" enabled=\"true\">\n" +
                "      <stringProp name=\"TestPlan.comments\"></stringProp>\n" +
                "      <boolProp name=\"TestPlan.functional_mode\">false</boolProp>\n" +
                "      <boolProp name=\"TestPlan.tearDown_on_shutdown\">true</boolProp>\n" +
                "      <boolProp name=\"TestPlan.serialize_threadgroups\">false</boolProp>\n" +
                "      <elementProp name=\"TestPlan.user_defined_variables\" elementType=\"Arguments\" guiclass=\"ArgumentsPanel\" testclass=\"Arguments\" testname=\"User Defined Variables\" enabled=\"true\">\n" +
                "        <collectionProp name=\"Arguments.arguments\"/>\n" +
                "      </elementProp>\n" +
                "      <stringProp name=\"TestPlan.user_define_classpath\"></stringProp>\n" +
                "    </TestPlan>\n";
        return text;
    }

    public static String crontrolSetting(Integer preNumber, Integer preTime) {
        String text = "<hashTree>\n" +
                "      <ThreadGroup guiclass=\"ThreadGroupGui\" testclass=\"ThreadGroup\" testname=\"首评心智\" enabled=\"true\">\n" +
                "        <stringProp name=\"ThreadGroup.on_sample_error\">continue</stringProp>\n" +
                "        <elementProp name=\"ThreadGroup.main_controller\" elementType=\"LoopController\" guiclass=\"LoopControlPanel\" testclass=\"LoopController\" testname=\"Loop Controller\" enabled=\"true\">\n" +
                "          <boolProp name=\"LoopController.continue_forever\">false</boolProp>\n" +
                "          <intProp name=\"LoopController.loops\">-1</intProp>\n" +
                "        </elementProp>\n" +
                "        <stringProp name=\"ThreadGroup.num_threads\">%d</stringProp>\n" +
                "        <stringProp name=\"ThreadGroup.ramp_time\">1</stringProp>\n" +
                "        <boolProp name=\"ThreadGroup.scheduler\">true</boolProp>\n" +
                "        <stringProp name=\"ThreadGroup.duration\">%d</stringProp>\n" +
                "        <stringProp name=\"ThreadGroup.delay\"></stringProp>\n" +
                "      </ThreadGroup>";
        text = String.format(text, preNumber, preTime);
        return text;
    }

    public static String httpRequestSetting(String interfaceName, String url, String requestType, String params, String timeOut) {

        if (url.contains("&")) {
            url = url.replace("&", "&amp;");
        }
        String paramskeyText = "<hashTree>\n" +
                "        <HTTPSamplerProxy guiclass=\"HttpTestSampleGui\" testclass=\"HTTPSamplerProxy\" testname=\"%s\" enabled=\"true\">\n" +
                "          <elementProp name=\"HTTPsampler.Arguments\" elementType=\"Arguments\" guiclass=\"HTTPArgumentsPanel\" testclass=\"Arguments\" testname=\"用户定义的变量\" enabled=\"true\">\n" +
                "            <collectionProp name=\"Arguments.arguments\">\n" +
                "              %s</collectionProp>\n" +
                "          </elementProp>\n" +
                "          <stringProp name=\"HTTPSampler.domain\"></stringProp>\n" +
                "          <stringProp name=\"HTTPSampler.port\"></stringProp>\n" +
                "          <stringProp name=\"HTTPSampler.connect_timeout\">%s</stringProp>\n" +
                "          <stringProp name=\"HTTPSampler.response_timeout\"></stringProp>\n" +
                "          <stringProp name=\"HTTPSampler.protocol\"></stringProp>\n" +
                "          <stringProp name=\"HTTPSampler.contentEncoding\"></stringProp>\n" +
                "          <stringProp name=\"HTTPSampler.path\">%s\n" +
                "          <stringProp name=\"HTTPSampler.method\">%s</stringProp>\n" +
                "          <boolProp name=\"HTTPSampler.follow_redirects\">true</boolProp>\n" +
                "          <boolProp name=\"HTTPSampler.auto_redirects\">false</boolProp>\n" +
                "          <boolProp name=\"HTTPSampler.use_keepalive\">true</boolProp>\n" +
                "          <boolProp name=\"HTTPSampler.DO_MULTIPART_POST\">false</boolProp>\n" +
                "          <boolProp name=\"HTTPSampler.monitor\">false</boolProp>\n" +
                "          <stringProp name=\"HTTPSampler.embedded_url_re\"></stringProp>\n" +
                "        </HTTPSamplerProxy>";
        String jsonKeyText = "<hashTree>\n" +
                "<HTTPSamplerProxy guiclass=\"HttpTestSampleGui\" testclass=\"HTTPSamplerProxy\" testname=\"%s\" enabled=\"true\">\n" +
                "          <boolProp name=\"HTTPSampler.postBodyRaw\">true</boolProp>\n" +
                "          <elementProp name=\"HTTPsampler.Arguments\" elementType=\"Arguments\">\n" +
                "            <collectionProp name=\"Arguments.arguments\">\n" +
                "              <elementProp name=\"\" elementType=\"HTTPArgument\">\n" +
                "                <boolProp name=\"HTTPArgument.always_encode\">false</boolProp>\n" +
                "                <stringProp name=\"Argument.value\">%s</stringProp>\n" +
                "                <stringProp name=\"Argument.metadata\">=</stringProp>\n" +
                "              </elementProp>\n" +
                "            </collectionProp>\n" +
                "          </elementProp>\n" +
                "          <stringProp name=\"HTTPSampler.domain\"></stringProp>\n" +
                "          <stringProp name=\"HTTPSampler.port\"></stringProp>\n" +
                "          <stringProp name=\"HTTPSampler.connect_timeout\">%s</stringProp>\n" +
                "          <stringProp name=\"HTTPSampler.response_timeout\"></stringProp>\n" +
                "          <stringProp name=\"HTTPSampler.protocol\"></stringProp>\n" +
                "          <stringProp name=\"HTTPSampler.contentEncoding\"></stringProp>\n" +
                "          <stringProp name=\"HTTPSampler.path\">%s</stringProp>\n" +
                "          <stringProp name=\"HTTPSampler.method\">%s</stringProp>\n" +
                "          <boolProp name=\"HTTPSampler.follow_redirects\">true</boolProp>\n" +
                "          <boolProp name=\"HTTPSampler.auto_redirects\">false</boolProp>\n" +
                "          <boolProp name=\"HTTPSampler.use_keepalive\">true</boolProp>\n" +
                "          <boolProp name=\"HTTPSampler.DO_MULTIPART_POST\">false</boolProp>\n" +
                "          <boolProp name=\"HTTPSampler.monitor\">false</boolProp>\n" +
                "          <stringProp name=\"HTTPSampler.embedded_url_re\"></stringProp>\n" +
                "        </HTTPSamplerProxy>";
        String httpText = "<hashTree>\n" +
                "        <HTTPSamplerProxy guiclass=\"HttpTestSampleGui\" testclass=\"HTTPSamplerProxy\" testname=\"%s\" enabled=\"true\">\n" +
                "          <elementProp name=\"HTTPsampler.Arguments\" elementType=\"Arguments\" guiclass=\"HTTPArgumentsPanel\" testclass=\"Arguments\" testname=\"用户定义的变量\" enabled=\"true\">\n" +
                "            <collectionProp name=\"Arguments.arguments\"/>\n" +
                "          </elementProp>\n" +
                "          <stringProp name=\"HTTPSampler.domain\"></stringProp>\n" +
                "          <stringProp name=\"HTTPSampler.port\"></stringProp>\n" +
                "          <stringProp name=\"HTTPSampler.connect_timeout\">%s</stringProp>\n" +
                "          <stringProp name=\"HTTPSampler.response_timeout\"></stringProp>\n" +
                "          <stringProp name=\"HTTPSampler.protocol\"></stringProp>\n" +
                "          <stringProp name=\"HTTPSampler.contentEncoding\"></stringProp>\n" +
                "          <stringProp name=\"HTTPSampler.path\">%s</stringProp>\n" +
                "          <stringProp name=\"HTTPSampler.method\">%s</stringProp>\n" +
                "          <boolProp name=\"HTTPSampler.follow_redirects\">true</boolProp>\n" +
                "          <boolProp name=\"HTTPSampler.auto_redirects\">false</boolProp>\n" +
                "          <boolProp name=\"HTTPSampler.use_keepalive\">true</boolProp>\n" +
                "          <boolProp name=\"HTTPSampler.DO_MULTIPART_POST\">false</boolProp>\n" +
                "          <boolProp name=\"HTTPSampler.monitor\">false</boolProp>\n" +
                "          <stringProp name=\"HTTPSampler.embedded_url_re\"></stringProp>\n" +
                "        </HTTPSamplerProxy>";
        Map<String, String> paramMap = new HashMap<>();
        String paramsTmp = "";
        String paramText = "<elementProp name=\"%s\" elementType=\"HTTPArgument\">\n" +
                "                <boolProp name=\"HTTPArgument.always_encode\">false</boolProp>\n" +
                "                <stringProp name=\"Argument.value\">%s</stringProp>\n" +
                "                <stringProp name=\"Argument.metadata\">=</stringProp>\n" +
                "                <boolProp name=\"HTTPArgument.use_equals\">true</boolProp>\n" +
                "                <stringProp name=\"Argument.name\">%s</stringProp>\n" +
                "              </elementProp>";
        if (requestType.toUpperCase().equals("POST")) {
            if (StringUtils.isNoneBlank(params) && params != null) {
                if (params.contains("paramskey") && params.contains("paramsvalue")) {
                    JSONArray json = JSON.parseArray(params);
                    for (int i = 0; i < json.size(); i++) {
                        Map<String, String> js = (Map<String, String>) json.get(i);
                        String paramskey = js.get("paramskey");
                        String paramsvalue = js.get("paramsvalue");
                        paramMap.put(paramskey, paramsvalue);
                    }
                    for (Map.Entry<String, String> entry : paramMap.entrySet()) {
                        String value = entry.getValue();
                        if (value.contains("\"")) {
                            value = value.replace("\"", "&quot;");
                        }
                        paramsTmp += String.format(paramText, entry.getKey(), value, entry.getKey());
                    }

                    return String.format(paramskeyText, interfaceName, paramsTmp, timeOut, url, requestType.toUpperCase());
                } else {
                    String tmpValue = "";
                    if (params.contains("\"")) {
                        tmpValue = params.replace("\"", "&quot;");
                    }
                    return String.format(jsonKeyText, interfaceName, tmpValue, timeOut, url, requestType.toUpperCase());
                }
            } else {
                return String.format(jsonKeyText, interfaceName, "", timeOut, url, requestType.toUpperCase());
            }
        } else {
            return String.format(httpText, interfaceName, timeOut, url, requestType.toUpperCase());
        }

    }

    public static String headerSetting(String header) {
        Map<String, String> formMap = new HashMap<>();
        String tmpText = "";
        String tmp = "<elementProp name=\"\" elementType=\"Header\">\n" +
                "                <stringProp name=\"Header.name\">%s</stringProp>\n" +
                "                <stringProp name=\"Header.value\">%s</stringProp>\n" +
                "              </elementProp>";

        String text = " <hashTree>\n" +
                "          <HeaderManager guiclass=\"HeaderPanel\" testclass=\"HeaderManager\" testname=\"HTTP Header Manager\" enabled=\"true\">\n" +
                "            <collectionProp name=\"HeaderManager.headers\">\n" +
                "              %s"+
                "            </collectionProp>\n" +
                "          </HeaderManager>\n" +
                "          <hashTree/>";
        if (StringUtils.isNoneBlank(header)) {
            if (header.contains("headerKey") && header.contains("headerValue")) {
                JSONArray json = JSON.parseArray(header);
                for (int i = 0; i < json.size(); i++) {
                    Map<String, String> js = (Map<String, String>) json.get(i);
                    String headerKey = js.get("headerKey");
                    String headerValue = js.get("headerValue");
                    formMap.put(headerKey, headerValue);
                }
                for (Map.Entry<String, String> entry : formMap.entrySet()) {
                    tmpText += String.format(tmp, entry.getKey(), entry.getValue());
                }
                return String.format(text, tmpText);
            }

        }
        return String.format(text, "");
    }

    public static String cookieSetting(String cookie, String url) {
        String tmpText = "";
        String pattern = "^((http://)|(https://))?([a-zA-Z0-9]([a-zA-Z0-9\\-]{0,61}[a-zA-Z0-9])?\\.)+[a-zA-Z]{2,6}(/)";
        Pattern p = Pattern.compile(pattern);
        Matcher matcher = p.matcher(url);
        matcher.find();
        String domain = matcher.group();
        if (domain.contains("http://")){
            domain = domain.split("http://")[1].replace("/","");
        }else if (domain.contains("https://")){
            domain = domain.split("https://")[1].replace("/","");
        }
        String tmp = " <elementProp name=\"%s\" elementType=\"Cookie\" testname=\"%s\">\n" +
                "                <stringProp name=\"Cookie.value\">%s</stringProp>\n" +
                "                <stringProp name=\"Cookie.domain\">%s</stringProp>\n" +
                "                <stringProp name=\"Cookie.path\">/</stringProp>\n" +
                "                <boolProp name=\"Cookie.secure\">false</boolProp>\n" +
                "                <longProp name=\"Cookie.expires\">0</longProp>\n" +
                "                <boolProp name=\"Cookie.path_specified\">true</boolProp>\n" +
                "                <boolProp name=\"Cookie.domain_specified\">true</boolProp>\n" +
                "              </elementProp>";
        String text = "<CookieManager guiclass=\"CookiePanel\" testclass=\"CookieManager\" testname=\"HTTP Cookie Manager\" enabled=\"true\">\n" +
                "            <collectionProp name=\"CookieManager.cookies\">\n" +
                "              %s" +
                "            </collectionProp>\n" +
                "            <boolProp name=\"CookieManager.clearEachIteration\">false</boolProp>\n" +
                "          </CookieManager>\n" +
                "<hashTree/>\n";
        Map<String, String> formMap = new HashMap<>();
        if (StringUtils.isNoneBlank(cookie) && cookie != null) {
            if (cookie.contains("cookieKey") && cookie.contains("cookieValue")) {
                JSONArray json = JSON.parseArray(cookie);
                for (int i = 0; i < json.size(); i++) {
                    Map<String, String> js = (Map<String, String>) json.get(i);
                    String headerKey = js.get("cookieKey");
                    String headerValue = js.get("cookieValue");
                    formMap.put(headerKey, headerValue);
                }
                for (Map.Entry<String, String> entry : formMap.entrySet()) {
                    tmpText += String.format(tmp, entry.getKey(), entry.getKey(),entry.getValue(), domain);
                }
                return String.format(text, tmpText);
            }
        }
        return String.format(text, "");
    }

    public static String preProcessorSetting(){
        String text = "<JSR223PreProcessor guiclass=\"TestBeanGUI\" testclass=\"JSR223PreProcessor\" testname=\"JSR223 PreProcessor\" enabled=\"true\">\n" +
                "            <stringProp name=\"cacheKey\">true</stringProp>\n" +
                "            <stringProp name=\"filename\"></stringProp>\n" +
                "            <stringProp name=\"parameters\"></stringProp>\n" +
                "            <stringProp name=\"script\">import java.util.UUID;\n" +
                " \n" +
                " String uuid = UUID.randomUUID().toString();\n" +
                "        uuid = uuid.substring(0, 8)+uuid.substring(9,13)+uuid.substring(14,18)+uuid.substring(19,23)+uuid.substring(24);\n" +
                "        StringBuffer stringBuffer = new StringBuffer();\n" +
                "        for (int i=0;i&lt;uuid.length();i++){\n" +
                "            if (!Character.isDigit(uuid.charAt(i))){\n" +
                "                int value = uuid.charAt(i);\n" +
                "                stringBuffer.append(value);\n" +
                "                continue;\n" +
                "            }\n" +
                "            stringBuffer.append(uuid.charAt(i));\n" +
                "        }\n" +
                "   uuid = stringBuffer.toString().substring(5,14);     \n" +
                "vars.put(&quot;requestid&quot;, uuid);</stringProp>\n" +
                "            <stringProp name=\"scriptLanguage\">groovy</stringProp>\n" +
                "          </JSR223PreProcessor>\n" +
                "          <hashTree/>";
        return text;
    }


    public static String responseAssertSetting(String assertText) {
        String text = "<hashTree/>\n" +
                "          <ResponseAssertion guiclass=\"AssertionGui\" testclass=\"ResponseAssertion\" testname=\"响应断言\" enabled=\"true\">\n" +
                "            <collectionProp name=\"Asserion.test_strings\">\n" +
                "              <stringProp name=\"67791721\">%s</stringProp>\n" +
                "            </collectionProp>\n" +
                "            <stringProp name=\"Assertion.test_field\">Assertion.response_data</stringProp>\n" +
                "            <boolProp name=\"Assertion.assume_success\">false</boolProp>\n" +
                "            <intProp name=\"Assertion.test_type\">16</intProp>\n" +
                "          </ResponseAssertion>\n"+
                "<hashTree/>";
        if (assertText.contains("\"")) {
            assertText = assertText.replace("\"", "&quot;");
        }
        return String.format(text, assertText);
    }

    public static String resultTreeSetting(){
        String text = " <ResultCollector guiclass=\"ViewResultsFullVisualizer\" testclass=\"ResultCollector\" testname=\"View Results Tree\" enabled=\"true\">\n" +
                "            <boolProp name=\"ResultCollector.error_logging\">false</boolProp>\n" +
                "            <objProp>\n" +
                "              <name>saveConfig</name>\n" +
                "              <value class=\"SampleSaveConfiguration\">\n" +
                "                <time>true</time>\n" +
                "                <latency>true</latency>\n" +
                "                <timestamp>true</timestamp>\n" +
                "                <success>true</success>\n" +
                "                <label>true</label>\n" +
                "                <code>true</code>\n" +
                "                <message>true</message>\n" +
                "                <threadName>true</threadName>\n" +
                "                <dataType>true</dataType>\n" +
                "                <encoding>false</encoding>\n" +
                "                <assertions>true</assertions>\n" +
                "                <subresults>true</subresults>\n" +
                "                <responseData>false</responseData>\n" +
                "                <samplerData>false</samplerData>\n" +
                "                <xml>false</xml>\n" +
                "                <fieldNames>true</fieldNames>\n" +
                "                <responseHeaders>false</responseHeaders>\n" +
                "                <requestHeaders>false</requestHeaders>\n" +
                "                <responseDataOnError>false</responseDataOnError>\n" +
                "                <saveAssertionResultsFailureMessage>true</saveAssertionResultsFailureMessage>\n" +
                "                <assertionsResultsToSave>0</assertionsResultsToSave>\n" +
                "                <bytes>true</bytes>\n" +
                "                <sentBytes>true</sentBytes>\n" +
                "                <url>true</url>\n" +
                "                <threadCounts>true</threadCounts>\n" +
                "                <idleTime>true</idleTime>\n" +
                "                <connectTime>true</connectTime>\n" +
                "              </value>\n" +
                "            </objProp>\n" +
                "            <stringProp name=\"filename\"></stringProp>\n" +
                "          </ResultCollector>\n";
        return text;
    }

    public static String aggregateGraphSetting(){
        String text = " <hashTree/>\n" +
                "          <ResultCollector guiclass=\"StatGraphVisualizer\" testclass=\"ResultCollector\" testname=\"Aggregate Graph\" enabled=\"true\">\n" +
                "            <boolProp name=\"ResultCollector.error_logging\">false</boolProp>\n" +
                "            <objProp>\n" +
                "              <name>saveConfig</name>\n" +
                "              <value class=\"SampleSaveConfiguration\">\n" +
                "                <time>true</time>\n" +
                "                <latency>true</latency>\n" +
                "                <timestamp>true</timestamp>\n" +
                "                <success>true</success>\n" +
                "                <label>true</label>\n" +
                "                <code>true</code>\n" +
                "                <message>true</message>\n" +
                "                <threadName>true</threadName>\n" +
                "                <dataType>true</dataType>\n" +
                "                <encoding>false</encoding>\n" +
                "                <assertions>true</assertions>\n" +
                "                <subresults>true</subresults>\n" +
                "                <responseData>false</responseData>\n" +
                "                <samplerData>false</samplerData>\n" +
                "                <xml>false</xml>\n" +
                "                <fieldNames>true</fieldNames>\n" +
                "                <responseHeaders>false</responseHeaders>\n" +
                "                <requestHeaders>false</requestHeaders>\n" +
                "                <responseDataOnError>false</responseDataOnError>\n" +
                "                <saveAssertionResultsFailureMessage>true</saveAssertionResultsFailureMessage>\n" +
                "                <assertionsResultsToSave>0</assertionsResultsToSave>\n" +
                "                <bytes>true</bytes>\n" +
                "                <sentBytes>true</sentBytes>\n" +
                "                <url>true</url>\n" +
                "                <threadCounts>true</threadCounts>\n" +
                "                <idleTime>true</idleTime>\n" +
                "                <connectTime>true</connectTime>\n" +
                "              </value>\n" +
                "            </objProp>\n" +
                "            <stringProp name=\"filename\"></stringProp>\n" +
                "          </ResultCollector>";
        return text;
    }

    public static String backendListener(boolean isActive){
        String text = "<BackendListener guiclass=\"BackendListenerGui\" testclass=\"BackendListener\" testname=\"Backend Listener\" enabled=\"%s\">\n" +
                "            <elementProp name=\"arguments\" elementType=\"Arguments\" guiclass=\"ArgumentsPanel\" testclass=\"Arguments\" enabled=\"true\">\n" +
                "              <collectionProp name=\"Arguments.arguments\">\n" +
                "                <elementProp name=\"graphiteMetricsSender\" elementType=\"Argument\">\n" +
                "                  <stringProp name=\"Argument.name\">graphiteMetricsSender</stringProp>\n" +
                "                  <stringProp name=\"Argument.value\">org.apache.jmeter.visualizers.backend.graphite.TextGraphiteMetricsSender</stringProp>\n" +
                "                  <stringProp name=\"Argument.metadata\">=</stringProp>\n" +
                "                </elementProp>\n" +
                "                <elementProp name=\"graphiteHost\" elementType=\"Argument\">\n" +
                "                  <stringProp name=\"Argument.name\">graphiteHost</stringProp>\n" +
                "                  <stringProp name=\"Argument.value\">localhost</stringProp>\n" +
                "                  <stringProp name=\"Argument.metadata\">=</stringProp>\n" +
                "                </elementProp>\n" +
                "                <elementProp name=\"graphitePort\" elementType=\"Argument\">\n" +
                "                  <stringProp name=\"Argument.name\">graphitePort</stringProp>\n" +
                "                  <stringProp name=\"Argument.value\">2003</stringProp>\n" +
                "                  <stringProp name=\"Argument.metadata\">=</stringProp>\n" +
                "                </elementProp>\n" +
                "                <elementProp name=\"rootMetricsPrefix\" elementType=\"Argument\">\n" +
                "                  <stringProp name=\"Argument.name\">rootMetricsPrefix</stringProp>\n" +
                "                  <stringProp name=\"Argument.value\">jmeter.</stringProp>\n" +
                "                  <stringProp name=\"Argument.metadata\">=</stringProp>\n" +
                "                </elementProp>\n" +
                "                <elementProp name=\"summaryOnly\" elementType=\"Argument\">\n" +
                "                  <stringProp name=\"Argument.name\">summaryOnly</stringProp>\n" +
                "                  <stringProp name=\"Argument.value\">true</stringProp>\n" +
                "                  <stringProp name=\"Argument.metadata\">=</stringProp>\n" +
                "                </elementProp>\n" +
                "                <elementProp name=\"samplersList\" elementType=\"Argument\">\n" +
                "                  <stringProp name=\"Argument.name\">samplersList</stringProp>\n" +
                "                  <stringProp name=\"Argument.value\"></stringProp>\n" +
                "                  <stringProp name=\"Argument.metadata\">=</stringProp>\n" +
                "                </elementProp>\n" +
                "                <elementProp name=\"percentiles\" elementType=\"Argument\">\n" +
                "                  <stringProp name=\"Argument.name\">percentiles</stringProp>\n" +
                "                  <stringProp name=\"Argument.value\">90;95;99</stringProp>\n" +
                "                  <stringProp name=\"Argument.metadata\">=</stringProp>\n" +
                "                </elementProp>\n" +
                "              </collectionProp>\n" +
                "            </elementProp>\n" +
                "            <stringProp name=\"classname\">org.apache.jmeter.visualizers.backend.graphite.GraphiteBackendListenerClient</stringProp>\n" +
                "          </BackendListener>";
        return String.format(text,String.valueOf(isActive));

    }

    public static String endSetting(){
        return "<hashTree/>\n" +
                "        </hashTree>\n" +
                "      </hashTree>\n" +
                "    </hashTree>\n" +
                "  </hashTree>\n" +
                "</jmeterTestPlan>";
    }

    public static void main(String[] args) throws IOException {
        String hd = headerSetting();
        String ct = crontrolSetting(100,100);
        String a = httpRequestSetting("测试结果比对", "http://stupad-hotfix.xk12.cn/api/pad/xiaoyun/content",
                "POST", "{\n" +
                        "    \"kid\": \"100\",\n" +
                        "    \"ktype\": 1,\n" +
                        "    \"klevel\": 2\n" +
                        "}", "1000");
        String hds = headerSetting("[{\"headerKey\":\"requestid\",\"headerValue\":\"2132133121\"},{\"headerKey\":\"token\",\"headerValue\":\"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1aWQiOiIxMzA3MjciLCJzeXN0ZW1JZCI6IjgxOTUxMDk2NjA5Iiwib3JnSWQiOiI4MCIsInRpbWVzdGFtcCI6IjE1NjA5MzAyMDY4NDUifQ.bNoV9ARuwTsHr9Z7uQlE6-7CN7XIfwwoM3LaEBhFMtQ\"},{\"headerKey\":\"Content-Type\",\"headerValue\":\"application/json\"}]");
        String cookie = cookieSetting(null,"http://stupad-hotfix.xk12.cn/api/pad/xiaoyun/content");
        String processor = preProcessorSetting();
        String tree = resultTreeSetting();
        String jh = aggregateGraphSetting();
        String ast = responseAssertSetting("\"ecode\":0");
        String bck = backendListener(true);
        String end = endSetting();
        System.out.println(tree);
        System.out.println(jh);
        String result = hd+ct+a+hds+cookie+processor+tree+jh+ast+bck+end;
        OutputStream os = new FileOutputStream(new File("/Users/liuzhanhui/Documents/test3.jmx"));
        os.write(result.getBytes("utf-8"));
        os.close();

    }
}
