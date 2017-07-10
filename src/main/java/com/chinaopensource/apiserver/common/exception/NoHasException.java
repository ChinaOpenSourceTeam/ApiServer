package com.chinaopensource.apiserver.common.exception;

public class NoHasException extends BaseException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoHasException() {
	}
	
	public NoHasException(String message){
		super(message+"的值不存在");
	}
}
