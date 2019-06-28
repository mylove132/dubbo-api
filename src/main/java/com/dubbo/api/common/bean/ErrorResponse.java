package com.dubbo.api.common.bean;

import com.dubbo.api.common.constant.CommonConstant;

public class ErrorResponse extends BaseResponse {

    public ErrorResponse() {
        this.setCode(CommonConstant.OP_FAILED.getCode());
        String message = getMessage(CommonConstant.OP_FAILED.getMessage(), null);
        this.setMessage(message);
    }

    public ErrorResponse(CommonConstant commonConstant){
        this.setCode(commonConstant.getCode());
        this.setMessage(commonConstant.getMessage());
    }
    public ErrorResponse(String message) {
        this.setCode(CommonConstant.OP_FAILED.getCode());
        this.setMessage(message);
    }

    public ErrorResponse(String message, Object[] params) {
        this.setCode(CommonConstant.OP_FAILED.getCode());
        String msg = getMessage(message, params);
        this.setMessage(msg);
    }

    public ErrorResponse(Integer code, String message, Object[] params) {
        this.setCode(code);
        String msg = getMessage(message, params);
        this.setMessage(msg);
    }

}
