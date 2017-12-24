package com.chinaopensource.apiserver.common.configure;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 读取执行配置文件的类
 * create by lzl ON 2017/12/17
 */
@Configuration
@ConfigurationProperties(prefix = "opensource")
public class OpenSourceConfig {

    private Map<String,String> jwt = new HashMap<>();
    private Map<String,Integer> image = new HashMap<>();
    private Map<String,String> email = new HashMap<>();

    public Map<String, String> getJwt() {
        return jwt;
    }

    public void setJwt(Map<String, String> jwt) {
        this.jwt = jwt;
    }

    public Map<String, Integer> getImage() {
        return image;
    }

    public void setImage(Map<String, Integer> image) {
        this.image = image;
    }

    public Map<String, String> getEmail() {
        return email;
    }

    public void setEmail(Map<String, String> email) {
        this.email = email;
    }

    public String getJwtHeader(){
        return jwt.get("header");
    }

    public String getJwtSecret(){
        return jwt.get("secret");
    }

    public Integer getJwtExpiration(){
        return Integer.valueOf(jwt.get("expiration"));
    }

    public Integer getImageWidth(){
        return image.get("width");
    }

    public Integer getImageHeight(){
        return image.get("height");
    }

    public String getEmailSmtpHostKey(){
        return email.get("smtp-host-key");
    }

    public String getEmailSmtpHostValue(){
        return email.get("smtp-host-value");
    }

    public String getEmailSmtpProtocolKey(){
        return email.get("smtp-protocol-key");
    }

    public String getEmailSmtpProtocolValue(){
        return email.get("smtp-protocol-value");
    }

    public String getEmailSmtpPortKey(){
        return email.get("smtp-port-key");
    }

    public String getEmailSmtPortValue(){
        return email.get("smtp-port-value");
    }

    public String getEmailSmtpAuthKey(){
        return email.get("smtp-auth-key");
    }

    public String getEmailSmtpAuthValue(){
        return email.get("smtp-auth-value");
    }

    public String getEmailEncodePattern(){
        return email.get("encode-pattern");
    }

    public String getEmailContentPattern(){
        return email.get("content-pattern");
    }

    public String getAuth(){
        return email.get("auth");
    }

    public String getPasswd(){
        return email.get("passwd");
    }

}