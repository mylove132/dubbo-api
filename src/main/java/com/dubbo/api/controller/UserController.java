package com.dubbo.api.controller;

import com.dubbo.api.common.bean.ErrorResponse;
import com.dubbo.api.common.bean.PageInfo;
import com.dubbo.api.common.constant.CommonConstant;
import com.dubbo.api.common.constant.PermissionConstant;
import com.dubbo.api.common.util.MD5Util;
import com.dubbo.api.config.AuthPermission;
import com.dubbo.api.common.bean.BaseResponse;
import com.dubbo.api.common.bean.SuccessResponse;
import com.dubbo.api.common.exception.UnknowException;
import com.dubbo.api.dao.TokenMapper;
import com.dubbo.api.service.IUserService;
import com.dubbo.api.vo.Token;
import com.dubbo.api.vo.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2019-06-28:16:33
 * Modify date: 2019-06-28:16:33
 */

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private TokenMapper tokenMapper;

    @RequestMapping(value = "",method = RequestMethod.POST)
    public BaseResponse addUserController(User user){
        System.out.println(user.getName());
        System.out.println(user.getPassword());
        log.info("添加用户信息："+user.toString());
        if (userService.getUserByEmail(user.getEmail()) != null){
            return new ErrorResponse(CommonConstant.USER_EXIST);
        }
        if (user.getRoleId() == null){
            user.setRoleId(1);
        }
        if (user.getCreateTime() == null){
            user.setCreateTime(new Date());
        }
        try {
            log.info("添加用户成功");
            log.info("添加用户信息："+user.toString());
            return new SuccessResponse(userService.addUser(user));
        }catch (UnknowException e){
            throw new UnknowException("addUserController",e.getMessage());
        }
    }

    @AuthPermission(PermissionConstant.ADMINISTRATOR)
    @RequestMapping(value = "",method = RequestMethod.PUT)
    public BaseResponse updateUserController(User user){
        log.info("修改用户信息："+user.toString());
        if (user.getCreateTime() == null){
            user.setCreateTime(new Date());
        }
        try {
            return new SuccessResponse(userService.updateUser(user));
        }catch (UnknowException e){
            throw new UnknowException("updateUserController",e.getMessage());
        }
    }


    @RequestMapping(value = "",method = RequestMethod.GET)
    public BaseResponse userListController(@RequestParam(defaultValue = "1",value = "currentPage") Integer pageNum,
                                           @RequestParam(defaultValue = "10",value = "pageSize") Integer pageSize){
        return new SuccessResponse(new PageInfo(userService.getUserList(pageNum,pageSize)));
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public BaseResponse getUserInfo(@PathVariable Integer id){
        return new SuccessResponse(userService.getUserById(id));
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public BaseResponse deleteUserInfo(@PathVariable Integer id){
        return new SuccessResponse(userService.deleteUserById(id));
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public BaseResponse login(HttpServletRequest request){
        String account = request.getParameter("account");
        String password = request.getParameter("passwd");
        log.info("请求的用户名："+account+",密码："+password);
        User u1 = userService.getUserByAccount(account);
        User user = new User();
        if (u1 == null){
            user.setRoleId(1);
            user.setCreateTime(new Date());
            user.setEmail(account+"@okay.cn");
            user.setPassword("123456");
            user.setName(account);
            userService.addUser(user);
            user = userService.getUserByAccount(account);
        }else {
            user = u1;
        }
        String md5 = MD5Util.encrypt(user.getName());
        Token token = tokenMapper.selectByUserId(user.getId());
        if (token == null){
            try {
                Token tk = new Token();
                tk.setToken(md5);
                tk.setUserId(user.getId());
                tk.setUpdateTime(new Date());
                tokenMapper.insert(tk);
            }catch (Exception e){
                return new ErrorResponse(CommonConstant.ADD_TOKEN_FAILED);
            }

        }else {
            try {
                Token tk = new Token();
                tk.setUpdateTime(new Date());
                tk.setUserId(user.getId());
                tk.setToken(md5);
                tk.setId(token.getId());
                tokenMapper.updateByPrimaryKeySelective(tk);
            }catch (Exception e){
                return new ErrorResponse(CommonConstant.UPDATE_TOKEN_FAILED);
            }

        }
        Map<String,Object> result = new HashMap<String, Object>();
        result.put("token",md5);
        result.put("name",user.getName());
        result.put("user_id",user.getId());
        return new SuccessResponse(result);
    }
}
