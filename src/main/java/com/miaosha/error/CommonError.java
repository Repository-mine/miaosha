package com.miaosha.error;

public interface CommonError {
	int getErrCode();

	String getErrMsg();

	public CommonError setErrMsg(String errMsg);
}
