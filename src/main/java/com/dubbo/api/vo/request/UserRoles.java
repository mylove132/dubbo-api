package com.dubbo.api.vo.request;

import java.io.Serializable;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2019-07-23:18:42
 * Modify date: 2019-07-23:18:42
 */
public class UserRoles implements Serializable {

    private Integer userId;
    private Integer roleId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "UserRoles{" +
                "userId=" + userId +
                ", roleId=" + roleId +
                '}';
    }
}
