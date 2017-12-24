package com.chinaopensource.apiserver.common.util.email;

import com.chinaopensource.apiserver.common.configure.OpenSourceConfig;
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

/**
 * 发送邮件类
 * create by lzl ON 2017/12/11
 */
@Component
public class SendEmailUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(SendEmailUtil.class);

    @Autowired
    private OpenSourceConfig openSourceConfig;

    /**
     *
     * @param emailTo
     * @param emailVerificationCode
     * @return
     */
    public Boolean sendEmail(@NotEmpty String emailTo,@NotEmpty String emailVerificationCode){
//       邮件发送者登陆邮箱服务器的账号、密码。
        EmailAuth emailAuth = new EmailAuth(openSourceConfig.getAuth(),openSourceConfig.getPasswd());
        Properties properties = System.getProperties();
        properties.put(openSourceConfig.getEmailSmtpHostKey(),openSourceConfig.getEmailSmtpHostValue());
        properties.put(openSourceConfig.getEmailSmtpProtocolKey(),openSourceConfig.getEmailSmtpProtocolValue());
        properties.put(openSourceConfig.getEmailSmtpPortKey(),openSourceConfig.getEmailSmtPortValue());
        properties.put(openSourceConfig.getEmailSmtpAuthKey(),openSourceConfig.getEmailSmtpAuthValue());
//      发送邮件的会话对象
        Session emailSession = Session.getInstance(properties,emailAuth);
        MimeMessage mimeMessage = new MimeMessage(emailSession);
//       邮箱主题
        String emailSubject = "中国开源网站激活邮件";
//        组装邮件的内容
        StringBuilder sb = new StringBuilder();
        sb.append(emailVerificationCode);
        try {
//
            mimeMessage.setFrom(new InternetAddress(openSourceConfig.getAuth()));
//            设置收信人
            mimeMessage.setRecipients(Message.RecipientType.TO,InternetAddress.parse(emailTo));
//              设置暗送人
//            mimeMessage.setRecipients(Message.RecipientType.BCC,"");
//            设置抄送人
//            mimeMessage.setRecipients(Message.RecipientType.CC,"");
//            设置邮件的及其以及编码
            mimeMessage.setSubject(emailSubject,openSourceConfig.getEmailEncodePattern());
//            设置邮件内容及其编码方式
            mimeMessage.setContent(sb.toString(),openSourceConfig.getEmailContentPattern());
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
