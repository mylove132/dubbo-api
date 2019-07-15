package com.dubbo.api.service.impl;

import com.dubbo.api.dao.HistoryMapper;
import com.dubbo.api.service.IHistoryService;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
public class HistoryServiceImpl implements IHistoryService {

    @Autowired
    private HistoryMapper historyMapper;


    @Override
    public List findHistoryByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List historyList = historyMapper.listHistory();
        return historyList;
    }

    @Override
    public List findHistoryWithScriptIdByPage(Integer scriptId,Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List historyList = historyMapper.listHistoryByScriptId(scriptId);
        return historyList;
    }

    @Override
    public List searchScriptWithKeyWordByPage(String keyword, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List historyList = historyMapper.searchScriptByKeyWord(keyword);
        return historyList;
    }
}
