package com.chinaopensource.apiserver.common.constant;

public enum ErrorMessage {

	OK("成功",ErrorCode.OK),
	ERR_SYS_LOGIN_PASSWORD("用户名或密码错误",ErrorCode.ERR_SYS_LOGIN_PASSWORD),
	ERR_SYS_TOKEN_NONE("token 不存在",ErrorCode.ERR_SYS_TOKEN_NONE),
	ERR_SYS_TOKEN_INVALID("token 验证无效",ErrorCode.ERR_SYS_TOKEN_INVALID);
	
	
	private String message;
	private int code;
	
	private ErrorMessage(String message,int code) {
		this.message=message;
		this.code=code;
	}

	// 普通方法  
    public static String getMessage(int code,Object... args) {  
        for (ErrorMessage e : ErrorMessage.values()) {  
            if (e.getCode() == code) {  
                return String.format(e.message, args); 
            }  
        }  
        return null;  
    }  
	
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
	
}
