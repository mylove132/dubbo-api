package com.dubbo.api.controller;

import com.dubbo.api.common.bean.BaseResponse;
import com.dubbo.api.common.bean.SuccessResponse;
import com.dubbo.api.dao.CategoryMapper;
import com.dubbo.api.vo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2019-07-17:18:13
 * Modify date: 2019-07-17:18:13
 */
@RequestMapping("/api/category")
@RestController
public class CategoryCrontroller {


    @Autowired
    private CategoryMapper categoryMapper;

    @RequestMapping(value = "",method = RequestMethod.GET)
    public BaseResponse listCategoryCrontroller(){
        List<Category> categories = categoryMapper.listCategory();
        return new SuccessResponse(categories);
    }
}
