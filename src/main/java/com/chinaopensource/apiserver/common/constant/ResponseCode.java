package com.chinaopensource.apiserver.common.constant;

public enum  ResponseCode {
	OK(0,"请求成功"),
	ERROR(50001,"服务器处理失败"),
	ERR_SYS_LOGIN_PASSWORD(50002,"用户名或密码错误"),
	ERR_SYS_TOKEN_NONE(50003,"token 不存在"),
	ERR_SYS_TOKEN_INVALID(50004,"token 验证无效"),
	ERR_SYS_PARAMETER_VALIDATE(50005,"参数无效"),
	;

	ResponseCode(Integer code,String message){
		this.code = code;
		this.messgae = message;
	}

	private Integer code;
	private String messgae;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessgae() {
		return messgae;
	}

	public void setMessgae(String messgae) {
		this.messgae = messgae;
	}

}