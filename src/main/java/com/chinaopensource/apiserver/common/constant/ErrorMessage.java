package com.chinaopensource.apiserver.common.constant;

public enum ErrorMessage {

	SUCCESS("成功",ErrorCode.OK),
    FAIL("用户名或密码错误",ErrorCode.ERR_SYS_LOGIN_PASSWORD);
	
	private String message;
	private int code;
	
	private ErrorMessage(String message,int code) {
		this.message=message;
		this.code=code;
	}

	// 普通方法  
    public static String getMessage(int code) {  
        for (ErrorMessage e : ErrorMessage.values()) {  
            if (e.getCode() == code) {  
                return e.message;  
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
