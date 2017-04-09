package com.chinaopensource.apiserver.common.controller;
/**
 * 返回json数据的基本格式
 * 
 * @author lqw
 * 2017年4月8日 下午9:44:21
 */
public class ResponseBase {

	// 返回错误码
	private int code;
	// 返回错误信息
	private String message;
	// 返回数据
	private Object data;
	
	public ResponseBase(int code ,String message) {
		this.code=code;
		this.message=message;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	
}
