package com.dubbo.api.controller;

import com.dubbo.api.common.bean.ErrorResponse;
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

    @AuthPermission(PermissionConstant.ADMINISTRATOR)
    @RequestMapping(value = "",method = RequestMethod.POST)
    public BaseResponse addUserController(@RequestBody User user){
        log.info("添加用户信息："+user.toString());
        if (user.getCreateTime() == null){
            user.setCreateTime(new Date());
        }
        try {
            log.info("添加用户成功");
            return new SuccessResponse(userService.addUser(user));
        }catch (UnknowException e){
            throw new UnknowException("addUserController",e.getMessage());
        }
    }

    @AuthPermission(PermissionConstant.ADMINISTRATOR)
    @RequestMapping(value = "",method = RequestMethod.PUT)
    public BaseResponse updateUserController(@RequestBody User user){
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
    public BaseResponse userListController(){
        return new SuccessResponse(userService.getUserList());
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
    public BaseResponse login(String email, String password){
        log.info("请求的用户名："+email+",密码："+password);
        if (email == null || password == null || StringUtils.isBlank(email) || StringUtils.isBlank(password)){
            return new ErrorResponse(CommonConstant.USER_PASSWORD_NULL);
        }
        User user = userService.getUserByEmailAndPassword(email, password);
        if (user == null){
            log.info("用户不存在");
            return new ErrorResponse(CommonConstant.USER_PASSWORD_ERROR);
        }
        log.info("用户："+user.getName()+"登录成功");
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
