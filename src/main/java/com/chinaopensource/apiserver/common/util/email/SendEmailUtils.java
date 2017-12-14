package com.chinaopensource.apiserver.common.util.email;

import com.chinaopensource.apiserver.common.constant.Constants;
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Properties;

/**
 * 发送邮件类
 * create by lzl ON 2017/12/11
 */
public class SendEmailUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(SendEmailUtils.class);

    /**
     * 系统属性
     */
    private static final Properties properties = System.getProperties();
    /**
     * 邮件会话属性
     */
    private static Session emailSession;
    /**
     * MIME邮件对象
     */
    private static MimeMessage mimeMessage;

    private static final SendEmailUtils SEND_EMAIL_UTILS = new SendEmailUtils();

    private SendEmailUtils(){

    }

    public static Boolean sendEmail(@NotEmpty String userName, @NotEmpty String passwd, @NotEmpty String emailTo, @NotEmpty String emailSubject, @NotEmpty String emailBody){
        EmailAuth emailAuth = new EmailAuth(userName,passwd);
        properties.put(Constants.MAIL_SMTP_HOST_KEY,Constants.MAIL_SMTP_HOST_QQ_VALUE);
        properties.put(Constants.MAIL_SMTP_PROTOCOL_KEY,Constants.MAIL_SMTP_PROTOCOL_VALUE);
        properties.put(Constants.MAIL_SMTP_PORT_KEY,Constants.MAIL_SMTP_PORT_VALUE);
        properties.put(Constants.MAIL_SMTP_AUTH_KEY,Constants.MAIL_SMTP_AUTH_VALUE);
//      发送邮件的会话对象
        emailSession = Session.getInstance(properties,emailAuth);
        mimeMessage = new MimeMessage(emailSession);
        try {
//            发送邮件服务器的所在主机
            mimeMessage.setFrom(new InternetAddress(userName));
//            设置收信人
            mimeMessage.setRecipients(Message.RecipientType.TO,InternetAddress.parse(emailTo));
//              设置暗送人
//            mimeMessage.setRecipients(Message.RecipientType.BCC,"");
//            设置抄送人
//            mimeMessage.setRecipients(Message.RecipientType.CC,"");
//            设置邮件的及其以及编码
            mimeMessage.setSubject(emailSubject,Constants.EMAIL_ENCODE_PATTERN);
//            设置邮件内容及其编码方式
            mimeMessage.setContent(emailBody,Constants.EMAIL_CONTENT_PATTERN);
            mimeMessage.setSentDate(new Date());
            Transport.send(mimeMessage);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            LOGGER.error("发送邮件失败：{}",e);
            return false;
        }
    }

}
