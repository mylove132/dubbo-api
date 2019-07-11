package com.dubbo.api.common.bean;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2019-07-02:17:55
 * Modify date: 2019-07-02:17:55
 */
@Slf4j
public class DubboJmeterScript {


    public static String startSetting(){
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

    public static String crontrolSetting(Integer preNumber,Integer preTime){
        String text = " <hashTree>\n" +
                "      <ThreadGroup guiclass=\"ThreadGroupGui\" testclass=\"ThreadGroup\" testname=\"Thread Group\" enabled=\"true\">\n" +
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
        return String.format(text,preNumber,preTime);
    }

    public static String resultTreeSetting(){
        String text = "<hashTree>\n" +
                "          <ResultCollector guiclass=\"ViewResultsFullVisualizer\" testclass=\"ResultCollector\" testname=\"View Results Tree\" enabled=\"true\">\n" +
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
                "                <responseData>true</responseData>\n" +
                "                <samplerData>false</samplerData>\n" +
                "                <xml>true</xml>\n" +
                "                <fieldNames>true</fieldNames>\n" +
                "                <responseHeaders>false</responseHeaders>\n" +
                "                <requestHeaders>false</requestHeaders>\n" +
                "                <responseDataOnError>false</responseDataOnError>\n" +
                "                <saveAssertionResultsFailureMessage>true</saveAssertionResultsFailureMessage>\n" +
                "                <assertionsResultsToSave>0</assertionsResultsToSave>\n" +
                "                <bytes>true</bytes>\n" +
                "                <sentBytes>true</sentBytes>\n" +
                "                <url>true</url>\n" +
                "                <hostname>true</hostname>\n" +
                "                <threadCounts>true</threadCounts>\n" +
                "                <idleTime>true</idleTime>\n" +
                "                <connectTime>true</connectTime>\n" +
                "              </value>\n" +
                "            </objProp>\n" +
                "            <stringProp name=\"filename\"></stringProp>\n" +
                "          </ResultCollector>";
        return text;
    }

    public static String aggregateReportSetting(){
        String text = "<hashTree/>\n" +
                "          <ResultCollector guiclass=\"StatVisualizer\" testclass=\"ResultCollector\" testname=\"Aggregate Report\" enabled=\"true\">\n" +
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
                "                <responseData>true</responseData>\n" +
                "                <samplerData>false</samplerData>\n" +
                "                <xml>true</xml>\n" +
                "                <fieldNames>true</fieldNames>\n" +
                "                <responseHeaders>false</responseHeaders>\n" +
                "                <requestHeaders>false</requestHeaders>\n" +
                "                <responseDataOnError>false</responseDataOnError>\n" +
                "                <saveAssertionResultsFailureMessage>true</saveAssertionResultsFailureMessage>\n" +
                "                <assertionsResultsToSave>0</assertionsResultsToSave>\n" +
                "                <bytes>true</bytes>\n" +
                "                <sentBytes>true</sentBytes>\n" +
                "                <url>true</url>\n" +
                "                <hostname>true</hostname>\n" +
                "                <threadCounts>true</threadCounts>\n" +
                "                <idleTime>true</idleTime>\n" +
                "                <connectTime>true</connectTime>\n" +
                "              </value>\n" +
                "            </objProp>\n" +
                "            <stringProp name=\"filename\"></stringProp>\n" +
                "          </ResultCollector>\n";
        return text;
    }

    public static String dubboSetting(String interfaceName,String zkAddress,String timeOut,String version,
                                      String ins,String methodName,String paramType,String params){
        if (params.contains("\"")){
            params = params.replace("\"","&quot;");
        }
        String text = "<hashTree>\n" +
                "        <io.github.ningyu.jmeter.plugin.dubbo.sample.DubboSample guiclass=\"io.github.ningyu.jmeter.plugin.dubbo.gui.DubboSampleGui\" testclass=\"io.github.ningyu.jmeter.plugin.dubbo.sample.DubboSample\" testname=\"%s\" enabled=\"true\">\n" +
                "          <stringProp name=\"FIELD_DUBBO_REGISTRY_PROTOCOL\">zookeeper</stringProp>\n" +
                "          <stringProp name=\"FIELD_DUBBO_REGISTRY_GROUP\"></stringProp>\n" +
                "          <stringProp name=\"FIELD_DUBBO_RPC_PROTOCOL\">dubbo://</stringProp>\n" +
                "          <stringProp name=\"FIELD_DUBBO_ADDRESS\">%s</stringProp>\n" +
                "          <stringProp name=\"FIELD_DUBBO_TIMEOUT\">%s</stringProp>\n" +
                "          <stringProp name=\"FIELD_DUBBO_VERSION\">%s</stringProp>\n" +
                "          <stringProp name=\"FIELD_DUBBO_RETRIES\">0</stringProp>\n" +
                "          <stringProp name=\"FIELD_DUBBO_GROUP\"></stringProp>\n" +
                "          <stringProp name=\"FIELD_DUBBO_CONNECTIONS\">100</stringProp>\n" +
                "          <stringProp name=\"FIELD_DUBBO_LOADBALANCE\">random</stringProp>\n" +
                "          <stringProp name=\"FIELD_DUBBO_ASYNC\">sync</stringProp>\n" +
                "          <stringProp name=\"FIELD_DUBBO_CLUSTER\">failfast</stringProp>\n" +
                "          <stringProp name=\"FIELD_DUBBO_INTERFACE\">%s</stringProp>\n" +
                "          <stringProp name=\"FIELD_DUBBO_METHOD\">%s</stringProp>\n" +
                "          <intProp name=\"FIELD_DUBBO_METHOD_ARGS_SIZE\">1</intProp>\n" +
                "          <stringProp name=\"FIELD_DUBBO_METHOD_ARGS_PARAM_TYPE1\">%s</stringProp>\n" +
                "          <stringProp name=\"FIELD_DUBBO_METHOD_ARGS_PARAM_VALUE1\">%s</stringProp>\n"+
                "          </io.github.ningyu.jmeter.plugin.dubbo.sample.DubboSample>";
        return String.format(text,interfaceName,zkAddress,timeOut,version,ins,methodName,paramType,params);
    }

    public static String watchReportSetting(){
        String text = "<hashTree>\n" +
                "          <ResultCollector guiclass=\"ViewResultsFullVisualizer\" testclass=\"ResultCollector\" testname=\"察看结果树\" enabled=\"true\">\n" +
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
                "                <threadCounts>true</threadCounts>\n" +
                "                <idleTime>true</idleTime>\n" +
                "                <connectTime>true</connectTime>\n" +
                "              </value>\n" +
                "            </objProp>\n" +
                "            <stringProp name=\"filename\"></stringProp>\n" +
                "          </ResultCollector>";
        return text;
    }

    public static String jhReportSetting(){
        String text = " <hashTree/>\n" +
                "          <ResultCollector guiclass=\"StatVisualizer\" testclass=\"ResultCollector\" testname=\"聚合报告\" enabled=\"true\">\n" +
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
                "                <threadCounts>true</threadCounts>\n" +
                "                <idleTime>true</idleTime>\n" +
                "                <connectTime>true</connectTime>\n" +
                "              </value>\n" +
                "            </objProp>\n" +
                "            <stringProp name=\"filename\"></stringProp>\n" +
                "          </ResultCollector>";
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
                "          </ResponseAssertion>";
        if (assertText.contains("\"")) {
            assertText = assertText.replace("\"", "&quot;");
        }
        return String.format(text, assertText);
    }

    public static String preProccessorSetting(){
        String text = "<hashTree/>\n" +
                "          <JSR223PreProcessor guiclass=\"TestBeanGUI\" testclass=\"JSR223PreProcessor\" testname=\"JSR223 PreProcessor\" enabled=\"true\">\n" +
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
                "vars.put(&quot;requestid&quot;, uuid);  </stringProp>\n" +
                "            <stringProp name=\"scriptLanguage\">groovy</stringProp>\n" +
                "          </JSR223PreProcessor>";
        return text;
    }
    public static String backendListenerSetting(){
        String text = " <hashTree/>\n" +
                "          <BackendListener guiclass=\"BackendListenerGui\" testclass=\"BackendListener\" testname=\"Backend Listener\" enabled=\"true\">\n" +
                "            <elementProp name=\"arguments\" elementType=\"Arguments\" guiclass=\"ArgumentsPanel\" testclass=\"Arguments\" enabled=\"true\">\n" +
                "              <collectionProp name=\"Arguments.arguments\">\n" +
                "                <elementProp name=\"graphiteMetricsSender\" elementType=\"Argument\">\n" +
                "                  <stringProp name=\"Argument.name\">graphiteMetricsSender</stringProp>\n" +
                "                  <stringProp name=\"Argument.value\">org.apache.jmeter.visualizers.backend.graphite.TextGraphiteMetricsSender</stringProp>\n" +
                "                  <stringProp name=\"Argument.metadata\">=</stringProp>\n" +
                "                </elementProp>\n" +
                "                <elementProp name=\"graphiteHost\" elementType=\"Argument\">\n" +
                "                  <stringProp name=\"Argument.name\">graphiteHost</stringProp>\n" +
                "                  <stringProp name=\"Argument.value\"></stringProp>\n" +
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
        return text;
    }


    public static String endSetting(){
        String text = "<hashTree/>\n" +
                "        </hashTree>\n" +
                "      </hashTree>\n" +
                "    </hashTree>\n" +
                "  </hashTree>\n" +
                "</jmeterTestPlan>";
        return text;
    }
}
