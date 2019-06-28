package com.dubbo.api.controller;

import com.dubbo.api.common.bean.BaseResponse;
import com.dubbo.api.common.bean.SuccessResponse;
import com.dubbo.api.common.exception.UnknowException;
import com.dubbo.api.service.IUserService;
import com.dubbo.api.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2019-06-28:16:33
 * Modify date: 2019-06-28:16:33
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private IUserService userService;


    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public BaseResponse addUserController(@RequestBody User user){
        if (user.getCreateTime() == null){
            user.setCreateTime(new Date());
        }
        try {
            return new SuccessResponse(userService.addUser(user));
        }catch (UnknowException e){
            throw new UnknowException("addUserController",e.getMessage());
        }
    }

    @RequestMapping(value = "list",method = RequestMethod.GET)
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
}
