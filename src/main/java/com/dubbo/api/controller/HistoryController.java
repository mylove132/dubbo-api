package com.dubbo.api.controller;

import com.dubbo.api.common.bean.BaseResponse;
import com.dubbo.api.common.bean.PageInfo;
import com.dubbo.api.common.bean.SuccessResponse;
import com.dubbo.api.service.IHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2019-07-04:13:14
 * Modify date: 2019-07-04:13:14
 */
@Slf4j
@RestController
@RequestMapping("/api/history")
public class HistoryController {

    @Autowired
    private IHistoryService historyService;

    @RequestMapping(value = "",method = RequestMethod.GET)
    public BaseResponse listHisotryCrontroller(@RequestParam(defaultValue = "1",value = "currentPage") Integer pageNum,
                                               @RequestParam(defaultValue = "10",value = "pageSize") Integer pageSize){
        log.info("请求当前页数："+pageNum+"单页显示数："+pageSize);
        List historyList = historyService.findHistoryByPage(pageNum,pageSize);
        return new SuccessResponse(new PageInfo(historyList));

    }

    @RequestMapping(method = RequestMethod.GET,value = "/orderByScriptId")
    public BaseResponse listHistoryByScriptIdCrontoller(@RequestParam("scriptId") Integer scriptId,@RequestParam(defaultValue = "1",value = "currentPage") Integer pageNum,
                                                        @RequestParam(defaultValue = "10",value = "pageSize") Integer pageSize){
        log.info("通过脚本id过滤历史记录");
        List histories = historyService.findHistoryWithScriptIdByPage(scriptId,pageNum,pageSize);
        return new SuccessResponse(new PageInfo(histories));
    }

    @RequestMapping(method = RequestMethod.GET,value = "/search")
    public BaseResponse searchHistoryCrontoller(String keyword,@RequestParam(defaultValue = "1",value = "currentPage") Integer pageNum,
                                                @RequestParam(defaultValue = "10",value = "pageSize") Integer pageSize){
        List<Map<String,Object>> mapList = new ArrayList<>();
        List scripts = historyService.searchScriptWithKeyWordByPage(keyword,pageNum,pageSize);
        return new SuccessResponse(new PageInfo(scripts));
    }
}
