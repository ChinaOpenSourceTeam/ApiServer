package com.chinaopensource.apiserver.common.controller;

import com.chinaopensource.apiserver.common.constant.ResponseCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * controller全局异常的捕获类
 * create by lzl ON 2017/12/03
 */
@RestControllerAdvice
public class ControllerAdviceBase extends ControllerBase {

    @ExceptionHandler(value = Exception.class)
    public String handleException(Exception e){
        LOGGER.error("controller层全局异常捕获:{}",e);
        return renderOk(ResponseCode.ERROR,e);
    }
}
