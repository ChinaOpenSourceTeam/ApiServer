package com.chinaopensource.apiserver.common.util.email;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * 邮件的认证类
 * create by lzl ON 2017/12/11
 */
public class EmailAuth extends Authenticator {

    /**
     * 用户名
     */
    private String nickName;
    /**
     * 密码
     */
    private String passwd;
    /**
     * 注册时填写的邮箱
     */
    private String email;
    /**
     *  图形验证码
     */
    private String imageVerificationCode;

    public EmailAuth(String nickName, String passwd) {
        this.nickName = nickName;
        this.passwd = passwd;
    }

    public EmailAuth(String name, String passwd, String email, String imageVerificationCode) {
        this.nickName = name;
        this.passwd = passwd;
        this.email = email;
        this.imageVerificationCode = imageVerificationCode;
    }

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(this.nickName,this.passwd);
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageVerificationCode() {
        return imageVerificationCode;
    }

    public void setImageVerificationCode(String imageVerificationCode) {
        this.imageVerificationCode = imageVerificationCode;
    }
}
