package com.chinaopensource.apiserver.common.constant;

public final class Constants {

	// JWT 配置
	public static final String JWT_HEADER = "Authorization";
	public static final String JWT_SECRET = "mySecret";
	public static final int JWT_EXPIRATION = 30*60;//秒

	// 运行环境 profile
    public static final String SPRING_PROFILE_DEV = "dev";
    public static final String SPRING_PROFILE_QA = "qa";
    public static final String SPRING_PROFILE_ALI = "ali";
    
    // redis 分隔符
    public static final String REDIS_COLON = ":";
    // redis 所有 模糊匹配
    public static final String REDIS_ALL = "*";
    
    // 用户信息   token
    public static final String USERINFO_TOKEN = "token";
    // 用户信息 
    public static final String USERINFO_INFO = "info";
    
}
