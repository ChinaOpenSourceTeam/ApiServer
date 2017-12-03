package com.chinaopensource.apiserver.common.controller;

import com.alibaba.fastjson.JSON;
import com.chinaopensource.apiserver.common.constant.ResponseCode;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

/**
 * controller继承基本的类，存放公共的方法
 * 
 * @author lqw
 * 2017年4月8日 上午12:53:54
 */
@RestController
public class ControllerBase {

	/**
	 * hibernate validator 参数错误
	 * @param e
	 * @return
	 */
	@ExceptionHandler
	public String exception(BindException e) { 
		// 存放错误信息
		String errorMessage = "";
		for(FieldError field: e.getFieldErrors()){
			errorMessage+="参数:"+field.getField()+",值:"+field.getRejectedValue()+",验证失败原因:"+field.getDefaultMessage();
		}
        return renderError(ResponseCode.ERR_SYS_PARAMETER_VALIDATE,errorMessage);
    }

    protected String renderOk(){
		return JSON.toJSONString(new ResponseBase(ResponseCode.OK));
	}

	protected String  renderOk(ResponseCode code , Object object){
		return JSON.toJSONString(new ResponseBase(code,object));
	}

	protected String renderError(ResponseCode responseCode,String message){
		return JSON.toJSONString(new ResponseBase(responseCode,message));
	}
	protected String renderError(ResponseCode code){
		return JSON.toJSONString(new ResponseBase(code));
	}
	private String renderError(ResponseCode code,String message,Object data){
		return JSON.toJSONString(new ResponseBase(code,message,data));
	}
	private String renderError(ResponseCode code,Object data){
		return JSON.toJSONString(new ResponseBase(code,data));
	}


}
