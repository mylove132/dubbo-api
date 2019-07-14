package com.dubbo.api.service.impl;

import com.dubbo.api.dao.HistoryMapper;
import com.dubbo.api.service.IHistoryService;
import com.dubbo.api.vo.History;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryServiceImpl implements IHistoryService {

    @Autowired
    private HistoryMapper historyMapper;

    @Override
    public List<History> findHistoryByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return historyMapper.listHistory();
    }
}
