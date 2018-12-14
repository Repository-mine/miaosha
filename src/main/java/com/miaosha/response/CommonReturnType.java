package com.miaosha.response;

public class CommonReturnType {
	//返回状态:true / false
	private String status;
	//成功：返回前端需要的数据
	//失败：返回通用的错误码格式
	private Object data;

	public CommonReturnType(String status, Object data) {
		this.status = status;
		this.data = data;
	}

	public CommonReturnType() {
	}

	public static CommonReturnType creat(Object result) {
		return CommonReturnType.creat("success", result);
	}

	public static CommonReturnType creat(String status, Object result) {
		return new CommonReturnType(status, result);
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "CommonReturnType{" +
				"status='" + status + '\'' +
				", data=" + data +
				'}';
	}
}
