package com.chinaopensource.apiserver.common.constant;

public final class Constants {

	/**
	 * 
jwt:
  header: Authorization
  secret: mySecret
  expiration: 604800
	 */
	
	// JWT 配置
	public static final String JWT_HEADER = "Authorization";
	public static final String JWT_SECRET = "mySecret";
	public static final int JWT_EXPIRATION = 30*60;//秒
	
}
