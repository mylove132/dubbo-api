package com.dubbo.api.common.constant;

/**
 * 公共常量
 */
public enum CommonConstant {

    OP_SUCCESS(0, "operation.success"),
    OP_FAILED(1, "operation.failed");


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
