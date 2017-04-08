package com.chinaopensource.apiserver.common.controller;

import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.chinaopensource.apiserver.common.constant.ErrorCode;

/**
 * controller继承基本的类，存放公共的方法
 * 
 * @author lqw
 * 2017年4月8日 上午12:53:54
 */
@RestController
public class ControllerBase {

	protected ResponseBase rep ;
	
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
        // 返回错误信息
        rep=new ResponseBase(ErrorCode.ERR_SYS_PARAMETER_VALIDATE, "参数错误:"+errorMessage);
        return JSON.toJSONString(rep);   
    }   
}
