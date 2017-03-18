package com.chinaopensource.apiserver.common.controller;

import com.chinaopensource.apiserver.common.constant.ErrorCode;
import com.chinaopensource.apiserver.common.constant.ErrorMessage;

public class ControllerBase {

	protected ResponseBase rep = new ResponseBase(ErrorCode.OK, ErrorMessage.getMessage(ErrorCode.OK));
	
	
}
