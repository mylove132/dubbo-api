package com.dubbo.api.service;
import java.util.List;

public interface IHistoryService {

    List findHistoryByPage(Integer pageNum, Integer pageSize);
    List findHistoryWithScriptIdByPage(Integer scriptId,Integer pageNum, Integer pageSize);
    List searchScriptWithKeyWordByPage(String keyword,Integer pageNum, Integer pageSize);
}
