package com.miaosha.controller;

import com.miaosha.error.BusinessException;
import com.miaosha.error.EmBusinessError;
import com.miaosha.response.CommonReturnType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
//@ControllerAdvice
public class BaseController {
	//定义exceptionhandler解决未被controller层吸收的异常
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
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
		return CommonReturnType.creat("fail", respData);
	}
}
