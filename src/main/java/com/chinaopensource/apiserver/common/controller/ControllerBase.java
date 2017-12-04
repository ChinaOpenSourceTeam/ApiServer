package com.chinaopensource.apiserver.common.controller;

import com.alibaba.fastjson.JSON;
import com.chinaopensource.apiserver.common.constant.ResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    protected static final Logger LOGGER = LoggerFactory.getLogger(ControllerBase.class);
	/**
	 * controller层统一异常捕获
     *
	 * @param e
	 * @return
	 */
//	@ExceptionHandler(value = Exception.class)
//	public String exception(Exception e) {
//	    LOGGER.error("统一异常捕获：{}",e);
//        return renderError(ResponseCode.ERR_SYS_PARAMETER_VALIDATE,e.getMessage());
//    }

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
