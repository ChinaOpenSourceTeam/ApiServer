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
	
    // Spring profiles for development, test and production, see http://jhipster.github.io/profiles/
    public static final String SPRING_PROFILE_DEVELOPMENT = "dev";
    public static final String SPRING_PROFILE_TEST = "test";
    public static final String SPRING_PROFILE_PRODUCTION = "prod";
    
    // redis 分隔符
    public static final String REDIS_COLON = ":";
    // redis 所有 模糊匹配
    public static final String REDIS_ALL = "*";
    
    // 用户信息   token
    public static final String USERINFO_TOKEN = "token";
    // 用户信息 
    public static final String USERINFO_INFO = "info";
    
}
