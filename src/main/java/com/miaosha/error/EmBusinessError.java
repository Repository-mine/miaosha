package com.miaosha.error;

public enum EmBusinessError implements CommonError {
    USER_NOT_EXIST(10001, "用户不存在"),
    PARAMETER_VALIDATION_ERROR(10003, "参数不合法"),
    UNKNOWN_ERROR(10002, "异常错误"),
    USER_LOGIN_FAILED(10003, "账号或密码错误");
    private int errCode;
    private String errMsg;

    EmBusinessError(int errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    @Override
    public int getErrCode() {
        return this.errCode;
    }

    @Override
    public String getErrMsg() {
        return this.errMsg;
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        this.errMsg = errMsg;
        return this;
    }
}
