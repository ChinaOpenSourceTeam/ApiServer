package com.chinaopensource.apiserver.common.util.email;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * 邮件的认证类
 * create by lzl ON 2017/12/11
 */
public class EmailAuth extends Authenticator {

    private String name;
    private String passwd;

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
}
