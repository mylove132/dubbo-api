package com.dubbo.api.service;

import com.dubbo.api.vo.User;

import java.util.List;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2019-06-28:16:08
 * Modify date: 2019-06-28:16:08
 */
public interface IUserService {

    int deleteUserById(Integer id);

    int addUser(User user);

    List<User> getUserList();

    User getUserById(Integer userId);

    User getUserByEmail(String email);

    User getUserByEmailAndPassword(String email, String password);

    int updateUser(User user);


}
