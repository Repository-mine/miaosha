package com.miaosha.controller;

import com.miaosha.error.BusinessException;
import com.miaosha.error.EmBusinessError;
import com.miaosha.response.CommonReturnType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@RestControllerAdvice
public class ExceptionHandlerController {
    //定义exceptionhandler解决未被controller层吸收的异常
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public Object handlerException(HttpServletRequest req, Exception ex) {
        HashMap<String, Object> respData = new HashMap<>();
        if (ex instanceof BusinessException) {
            BusinessException exception = (BusinessException) ex;
            respData.put("errorCode", exception.getErrCode());
            respData.put("errorMsg", exception.getErrMsg());
        } else {
            respData.put("errorCode", EmBusinessError.UNKNOWN_ERROR.getErrCode());
            respData.put("errorMsg", EmBusinessError.UNKNOWN_ERROR.getErrMsg());
        }
        System.out.println(EmBusinessError.PARAMETER_VALIDATION_ERROR.getErrMsg() + EmBusinessError.PARAMETER_VALIDATION_ERROR.getErrCode());
        return CommonReturnType.creat("fail", respData);
    }
}
