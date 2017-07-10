package com.chinaopensource.apiserver.common.exception;

public class HasException extends BaseException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HasException() {
		super();
	}
	
	public HasException(String message) {
        super(message+"的值已经存在");
    }
}
