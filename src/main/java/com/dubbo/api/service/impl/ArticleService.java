package com.dubbo.api.service.impl;

import com.dubbo.api.dao.ArticleMapper;
import com.dubbo.api.service.IArticleService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2019-07-17:17:13
 * Modify date: 2019-07-17:17:13
 */
@Service
public class ArticleService implements IArticleService{

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public List getArticleListService(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return articleMapper.articleList();
    }
}
