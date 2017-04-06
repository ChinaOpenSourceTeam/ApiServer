package com.chinaopensource.apiserver.common.controller;

import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

@RestController
public class ControllerBase {

	protected ResponseBase rep ;
	
	@ExceptionHandler
	public String exception(BindException e) { 
		String errorMessage = "";
		for(FieldError field: e.getFieldErrors()){
			errorMessage+="参数:"+field.getField()+",值:"+field.getRejectedValue()+",验证失败原因:"+field.getDefaultMessage();
		}
        
        rep=new ResponseBase(1234, "自定义错误:"+errorMessage);
        return JSON.toJSONString(rep);   
    }   
}
