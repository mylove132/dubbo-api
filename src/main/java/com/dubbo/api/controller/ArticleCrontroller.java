package com.dubbo.api.controller;

import com.dubbo.api.common.bean.BaseResponse;
import com.dubbo.api.common.bean.PageInfo;
import com.dubbo.api.common.bean.SuccessResponse;
import com.dubbo.api.dao.ArticleMapper;
import com.dubbo.api.service.IArticleService;
import com.dubbo.api.vo.Article;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2019-07-17:17:02
 * Modify date: 2019-07-17:17:02
 */
@Slf4j
@RequestMapping("/api/article")
@RestController
public class ArticleCrontroller {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private IArticleService articleService;

    @RequestMapping(value = "",method = RequestMethod.GET)
    public BaseResponse listArticleCrontroller(@RequestParam(defaultValue = "1",value = "currentPage") Integer pageNum,
                                               @RequestParam(defaultValue = "10",value = "pageSize") Integer pageSize){
        List historyList = articleService.getArticleListService(pageNum,pageSize);
        return new SuccessResponse(new PageInfo(historyList));

    }

    @RequestMapping(value = "",method = RequestMethod.POST)
    public BaseResponse addArticleCrontroller(Article article){
        if (article.getCreateTime() == null){
            article.setCreateTime(new Date());
        }
        if (article.getUpdateTime() == null){
            article.setUpdateTime(new Date());
        }
        int result = articleMapper.insertSelective(article);
        return new SuccessResponse(result);
    }

    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    public BaseResponse deleteArticleCrontroller(@PathVariable Integer articleId){
        int result = articleMapper.deleteByPrimaryKey(articleId);
        return new SuccessResponse(result);
    }

    @RequestMapping(value = "",method = RequestMethod.PUT)
    public BaseResponse uArticleCrontroller(Article article){
        int result = articleMapper.updateByPrimaryKey(article);
        return new SuccessResponse(result);
    }


}
