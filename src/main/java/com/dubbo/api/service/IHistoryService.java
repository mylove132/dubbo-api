package com.dubbo.api.service;

import com.dubbo.api.vo.History;

import java.util.List;

public interface IHistoryService {

    List<History> findHistoryByPage(Integer pageNum, Integer pageSize);
}
