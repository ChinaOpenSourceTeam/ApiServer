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
    private String name;
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

    public EmailAuth(){

    }

    public EmailAuth(String name, String passwd) {
        this.name = name;
        this.passwd = passwd;
    }

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(this.name,this.passwd);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
