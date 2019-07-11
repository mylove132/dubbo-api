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
    ADD_PROJECT_FAIL(5001,"添加项目失败"),
    ADD_SCRIPT_FAIL(6001,"添加脚本失败"),
    PARAM_NULL_FAIL(10001,"参数不能为空"),
    WATCH_REPORT_NULL(6003,"报告不存在"),
    EXEC_FILE_EXIST(8001,"执行的jmeter文件已存在"),
    EXEC_COUNT_MAX(8002,"执行的jmeter文件最多同时执行2个"),
    CREATE_JMETER_FILE_FAIL(8003,"创建jmeter文件失败"),
    MODIFY_BUILD_FILE(8006,"修改构建文件失败"),
    REPORT_LOG_NOT_EXIST(8004,"日志文件不存在，可能已删除"),
    REPORT_GENERATE_FAIL(8005,"日志文件生成失败，请重试");


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
