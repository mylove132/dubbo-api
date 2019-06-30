package com.dubbo.api.common.constant;

/**
 * 公共常量
 */
public enum CommonConstant {

    OP_SUCCESS(0, "operation.success"),
    OP_FAILED(-1, "operation.failed"),
    USER_PASSWORD_NULL(4000,"用户名或密码为空"),
    USER_PASSWORD_ERROR(4001,"用户名密码错误"),
    ADD_TOKEN_FAILED(4003,"添加token失败"),
    UPDATE_TOKEN_FAILED(4004,"更新token失败"),
    ADD_PROJECT_FAIL(5001,"添加项目失败");


    CommonConstant(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;

    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
