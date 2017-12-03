package com.chinaopensource.apiserver.common.controller;

import com.alibaba.fastjson.JSON;
import com.chinaopensource.apiserver.common.constant.ErrorMessage;

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
	
	public ResponseBase(int code) {
		this.code=code;
	}
	
	public ResponseBase(int code ,String message) {
		this.code=code;
		this.message=message;
	}
	
	public ResponseBase(int code,String message,Object data){
		this.code = code;
		this.message = message;
		this.data = data;
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
	
	
	// 转换成json
	public String toJson(){
		return JSON.toJSONString(this);
	}
	
	/**
	 * 返回正确数据json
	 * @param data  数据
	 * @return
	 */
	public static String getSuccessJson(Object data){
		return new ResponseBase(0,"成功",data).toJson();
	}
	
	/**
	 * 返回正确数据json
	 * @param data  数据
	 * @return
	 */
	public static String getSuccessJson(){
		return getSuccessJson(null);
	}
	
	/**
     * 
     * @param code  错误码
     * @param args  错误信息参数
     * @return 返回错误json数据
     */
    public static String getErrorJson(int code ,Object... args){
    	ResponseBase rb = new ResponseBase(code);
    	for (ErrorMessage e : ErrorMessage.values()) {  
            if (e.getCode() == code) { 
                rb.setMessage(String.format(e.getMessage(), args));  
            }  
        }
    	return rb.toJson();
    }
}
