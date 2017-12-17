package com.chinaopensource.apiserver.common.configure;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 读取执行配置文件的类
 * create by lzl ON 2017/12/17
 */
@Component
@ConfigurationProperties(prefix = "email")
public class OpenSourceConfig {

    /**
     * smtp主机key
     */
    private String smtpHostKey;
    /**
     * smtp主机value
     */
    private String smtpHostValue;
    /**
     * smtp协议key
     */
    private String smtpProtocolKey;
    /**
     * smtp协议value
     */
    private String getSmtpProtocolValue;
    /**
     * smtp端口key
     */
    private String smtpPortKey;
    /**
     * smtp端口value
     */
    private String smtpPortValue;
    /**
     * smtp认证key
     */
    private String smtpAuthKey;
    /**
     * smtp认证value
     */
    private String smtpAuthValue;
    /**
     * 邮件编码格式
     */
    private String  encodePattern;
    /**
     * 邮件内容编码格式
     */
    private String contentPattern;

    public String getSmtpHostKey() {
        return smtpHostKey;
    }

    public void setSmtpHostKey(String smtpHostKey) {
        this.smtpHostKey = smtpHostKey;
    }

    public String getSmtpHostValue() {
        return smtpHostValue;
    }

    public void setSmtpHostValue(String smtpHostValue) {
        this.smtpHostValue = smtpHostValue;
    }

    public String getSmtpProtocolKey() {
        return smtpProtocolKey;
    }

    public void setSmtpProtocolKey(String smtpProtocolKey) {
        this.smtpProtocolKey = smtpProtocolKey;
    }

    public String getGetSmtpProtocolValue() {
        return getSmtpProtocolValue;
    }

    public void setGetSmtpProtocolValue(String getSmtpProtocolValue) {
        this.getSmtpProtocolValue = getSmtpProtocolValue;
    }

    public String getSmtpPortKey() {
        return smtpPortKey;
    }

    public void setSmtpPortKey(String smtpPortKey) {
        this.smtpPortKey = smtpPortKey;
    }

    public String getSmtpPortValue() {
        return smtpPortValue;
    }

    public void setSmtpPortValue(String smtpPortValue) {
        this.smtpPortValue = smtpPortValue;
    }

    public String getSmtpAuthKey() {
        return smtpAuthKey;
    }

    public void setSmtpAuthKey(String smtpAuthKey) {
        this.smtpAuthKey = smtpAuthKey;
    }

    public String getSmtpAuthValue() {
        return smtpAuthValue;
    }

    public void setSmtpAuthValue(String smtpAuthValue) {
        this.smtpAuthValue = smtpAuthValue;
    }

    public String getEncodePattern() {
        return encodePattern;
    }

    public void setEncodePattern(String encodePattern) {
        this.encodePattern = encodePattern;
    }

    public String getContentPattern() {
        return contentPattern;
    }

    public void setContentPattern(String contentPattern) {
        this.contentPattern = contentPattern;
    }
}
