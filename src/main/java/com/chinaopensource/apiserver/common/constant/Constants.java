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

    /**
     * smtp的主机名称
     */
    public static final String MAIL_SMTP_HOST_KEY = "mail.smtp.host";
    public static final String MAIL_SMTP_HOST_QQ_VALUE = "mx1.qq.com";
    /**
     * 协议
     */
    public static final String MAIL_SMTP_PROTOCOL_KEY = "mail.store.protocol";
    public static final String MAIL_SMTP_PROTOCOL_VALUE = "smtp";
    /**
     * smtp的port
     */
    public static final String MAIL_SMTP_PORT_KEY = "mail.smtp.port";
    public static final Integer MAIL_SMTP_PORT_VALUE = 25;
    /**
     * smtp认证 参数名
     */
    public static final String MAIL_SMTP_AUTH_KEY = "mail.smtp.auth";
    public static final String MAIL_SMTP_AUTH_VALUE = "true";
    /**
     * 邮件编码格式
     */
    public static final String EMAIL_ENCODE_PATTERN = "gb2312";
    /**
     * 邮件内容表编码格式
     */
    public static final String EMAIL_CONTENT_PATTERN = "text/html;charset=gb2312";


}
