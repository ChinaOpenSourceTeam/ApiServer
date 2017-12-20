package com.chinaopensource.apiserver.common.controller;

import com.alibaba.fastjson.JSON;
import com.chinaopensource.apiserver.common.constant.ResponseCode;
import com.google.common.collect.ImmutableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * controller继承基本的类，存放公共的方法
 * 
 * @author lqw
 * 2017年4月8日 上午12:53:54
 */
@RestController
public class ControllerBase {

    protected static final Logger LOGGER = LoggerFactory.getLogger(ControllerBase.class);

	protected String renderOk(ResponseCode code){
    	return JSON.toJSONString(new ResponseBase(code));
	}

	protected String  renderOk(ResponseCode code , Object object){
		return JSON.toJSONString(new ResponseBase(code,object));
	}

	protected String renderOk(ResponseCode responseCode,String message){
		return JSON.toJSONString(new ResponseBase(responseCode,message));
	}
	public Map<String, Object> mapOf(String key, Object  value){
        return ImmutableMap.of(key,value);
    }

    public Map<String,Object> mapOf(String keyOne,Object valueOne,String keyTwo,Object valueTwo){
        return ImmutableMap.of(keyOne,valueOne,keyTwo,valueTwo);
    }

    public Map<String,Object> mapOf(String k1,Object v1,String k2,Object v2,String k3,Object v3){
        return ImmutableMap.of(k1,v1,k2,v2,k3,v3);
    }

    public Map<String,Object> mapOf(String k1,Object v1,String k2,Object v2,String k3,Object v3,String k4,Object v4){
        return ImmutableMap.of(k1,v1,k2,v2,k3,v3,k4,v4);
    }

    public Map<String,Object> mapOf(String k1,Object v1,String k2,Object v2,String k3,Object v3,String k4,Object v4,String k5,Object v5){
        return ImmutableMap.of(k1,v1,k2,v2,k3,v3,k4,v4,k5,v5);
    }

}
