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
                "<jmeterTestPlan version=\"1.2\" properties=\"3.1\" jmeter=\"3.1 r1770033\">\n" +
                "  <hashTree>\n" +
                "    <TestPlan guiclass=\"TestPlanGui\" testclass=\"TestPlan\" testname=\"测试计划\" enabled=\"true\">\n" +
                "      <stringProp name=\"TestPlan.comments\"></stringProp>\n" +
                "      <boolProp name=\"TestPlan.functional_mode\">false</boolProp>\n" +
                "      <boolProp name=\"TestPlan.serialize_threadgroups\">false</boolProp>\n" +
                "      <elementProp name=\"TestPlan.user_defined_variables\" elementType=\"Arguments\" guiclass=\"ArgumentsPanel\" testclass=\"Arguments\" testname=\"用户定义的变量\" enabled=\"true\">\n" +
                "        <collectionProp name=\"Arguments.arguments\"/>\n" +
                "      </elementProp>\n" +
                "      <stringProp name=\"TestPlan.user_define_classpath\"></stringProp>\n" +
                "    </TestPlan>";
        return text;
    }

    public static String crontrolSetting(Integer preNumber,Integer preTime){
        String text = "    <hashTree>\n" +
                "      <ThreadGroup guiclass=\"ThreadGroupGui\" testclass=\"ThreadGroup\" testname=\"线程组\" enabled=\"true\">\n" +
                "        <stringProp name=\"ThreadGroup.on_sample_error\">continue</stringProp>\n" +
                "        <elementProp name=\"ThreadGroup.main_controller\" elementType=\"LoopController\" guiclass=\"LoopControlPanel\" testclass=\"LoopController\" testname=\"循环控制器\" enabled=\"true\">\n" +
                "          <boolProp name=\"LoopController.continue_forever\">false</boolProp>\n" +
                "          <intProp name=\"LoopController.loops\">-1</intProp>\n" +
                "        </elementProp>\n" +
                "        <stringProp name=\"ThreadGroup.num_threads\">%d</stringProp>\n" +
                "        <stringProp name=\"ThreadGroup.ramp_time\">1</stringProp>\n" +
                "        <longProp name=\"ThreadGroup.start_time\">1559613505000</longProp>\n" +
                "        <longProp name=\"ThreadGroup.end_time\">1559613505000</longProp>\n" +
                "        <boolProp name=\"ThreadGroup.scheduler\">true</boolProp>\n" +
                "        <stringProp name=\"ThreadGroup.duration\">%d</stringProp>\n" +
                "        <stringProp name=\"ThreadGroup.delay\"></stringProp>\n" +
                "      </ThreadGroup>";
        return String.format(text,preNumber,preTime);
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

    public static String rtotReportSetting(){
        String text = "<hashTree/>\n" +
                "          <kg.apc.jmeter.vizualizers.CorrectedResultCollector guiclass=\"kg.apc.jmeter.vizualizers.ResponseTimesOverTimeGui\" testclass=\"kg.apc.jmeter.vizualizers.CorrectedResultCollector\" testname=\"jp@gc - Response Times Over Time\" enabled=\"true\">\n" +
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
                "            <longProp name=\"interval_grouping\">500</longProp>\n" +
                "            <boolProp name=\"graph_aggregated\">false</boolProp>\n" +
                "            <stringProp name=\"include_sample_labels\"></stringProp>\n" +
                "            <stringProp name=\"exclude_sample_labels\"></stringProp>\n" +
                "            <stringProp name=\"start_offset\"></stringProp>\n" +
                "            <stringProp name=\"end_offset\"></stringProp>\n" +
                "            <boolProp name=\"include_checkbox_state\">false</boolProp>\n" +
                "            <boolProp name=\"exclude_checkbox_state\">false</boolProp>\n" +
                "          </kg.apc.jmeter.vizualizers.CorrectedResultCollector>";
        return text;
    }

    public static String tpsSetting(){
        String text = "<hashTree/>\n" +
                "          <kg.apc.jmeter.vizualizers.CorrectedResultCollector guiclass=\"kg.apc.jmeter.vizualizers.TransactionsPerSecondGui\" testclass=\"kg.apc.jmeter.vizualizers.CorrectedResultCollector\" testname=\"jp@gc - Transactions per Second\" enabled=\"true\">\n" +
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
                "            <longProp name=\"interval_grouping\">1000</longProp>\n" +
                "            <boolProp name=\"graph_aggregated\">false</boolProp>\n" +
                "            <stringProp name=\"include_sample_labels\"></stringProp>\n" +
                "            <stringProp name=\"exclude_sample_labels\"></stringProp>\n" +
                "            <stringProp name=\"start_offset\"></stringProp>\n" +
                "            <stringProp name=\"end_offset\"></stringProp>\n" +
                "            <boolProp name=\"include_checkbox_state\">false</boolProp>\n" +
                "            <boolProp name=\"exclude_checkbox_state\">false</boolProp>\n" +
                "          </kg.apc.jmeter.vizualizers.CorrectedResultCollector>";
        return text;
    }

    public static String pmcSetting(boolean isOpenServerMonitor,String host,String port) {
        String text = "<hashTree/>\n" +
                "          <kg.apc.jmeter.perfmon.PerfMonCollector guiclass=\"kg.apc.jmeter.vizualizers.PerfMonGui\" testclass=\"kg.apc.jmeter.perfmon.PerfMonCollector\" testname=\"jp@gc - PerfMon Metrics Collector\" enabled=\"%s\">\n" +
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
                "            <longProp name=\"interval_grouping\">1000</longProp>\n" +
                "            <boolProp name=\"graph_aggregated\">false</boolProp>\n" +
                "            <stringProp name=\"include_sample_labels\"></stringProp>\n" +
                "            <stringProp name=\"exclude_sample_labels\"></stringProp>\n" +
                "            <stringProp name=\"start_offset\"></stringProp>\n" +
                "            <stringProp name=\"end_offset\"></stringProp>\n" +
                "            <boolProp name=\"include_checkbox_state\">false</boolProp>\n" +
                "            <boolProp name=\"exclude_checkbox_state\">false</boolProp>\n" +
                "            <collectionProp name=\"metricConnections\">\n" +
                "              <collectionProp name=\"917712290\">\n" +
                "                <stringProp name=\"-1204607085\">%s</stringProp>\n" +
                "                <stringProp name=\"1600768\">%s</stringProp>\n" +
                "                <stringProp name=\"66952\">CPU</stringProp>\n" +
                "                <stringProp name=\"0\"></stringProp>\n" +
                "              </collectionProp>\n" +
                "              <collectionProp name=\"-723468740\">\n" +
                "                <stringProp name=\"-1204607085\">%s</stringProp>\n" +
                "                <stringProp name=\"1600768\">%s</stringProp>\n" +
                "                <stringProp name=\"2590131\">Swap</stringProp>\n" +
                "                <stringProp name=\"0\"></stringProp>\n" +
                "              </collectionProp>\n" +
                "              <collectionProp name=\"-1383002031\">\n" +
                "                <stringProp name=\"-1204607085\">%s</stringProp>\n" +
                "                <stringProp name=\"1600768\">%s</stringProp>\n" +
                "                <stringProp name=\"-1993889503\">Memory</stringProp>\n" +
                "                <stringProp name=\"0\"></stringProp>\n" +
                "              </collectionProp>\n" +
                "              <collectionProp name=\"-1270662015\">\n" +
                "                <stringProp name=\"-1204607085\">%s</stringProp>\n" +
                "                <stringProp name=\"1600768\">%s</stringProp>\n" +
                "                <stringProp name=\"-274342153\">Network I/O</stringProp>\n" +
                "                <stringProp name=\"0\"></stringProp>\n" +
                "              </collectionProp>\n" +
                "            </collectionProp>\n" +
                "          </kg.apc.jmeter.perfmon.PerfMonCollector>";

        return String.format(text,String.valueOf(isOpenServerMonitor),host,port,host,port,host,port,host,port);

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

    public static void main(String[] args) {
        String start = startSetting();
        String cron = crontrolSetting(200,200);
        String dubbo = dubboSetting("测试dubbo接口","10.10.6.3:2181","3000",
                "3.0.0","com.noriental.lessonsvr.rservice.LessonService","fetchLessonDetail","com.noriental.lessonsvr.entity.request.LongRequest","{\"id\":110}");
        String watch = watchReportSetting();
        String jh = jhReportSetting();
        String rtot = rtotReportSetting();
        String tps = tpsSetting();
        String pmc = pmcSetting(false,"10.10.6.1","4444");
        String end = endSetting();
        String result = start+cron+dubbo+watch+jh+responseAssertSetting("\"code\":0")+rtot+tps+pmc+end;
        try {
            OutputStream os = new FileOutputStream(new File("/Users/liuzhanhui/Documents/test4.jmx"));
            os.write(result.getBytes("utf-8"));
            os.close();
        }catch (IOException e){
            log.error("jmx文件写入错误");
        }
    }
}
