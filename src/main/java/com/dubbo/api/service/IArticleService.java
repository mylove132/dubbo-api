package com.dubbo.api.service;

import java.util.List;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2019-07-17:17:12
 * Modify date: 2019-07-17:17:12
 */
public interface IArticleService {

    List getArticleListService(Integer pageNum, Integer pageSize);
}
