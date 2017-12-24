package com.chinaopensource.apiserver.test;

import com.chinaopensource.apiserver.common.configure.OpenSourceConfig;
import com.chinaopensource.apiserver.common.constant.EncryptionEnum;
import com.chinaopensource.apiserver.common.controller.ControllerBase;
import com.chinaopensource.apiserver.common.util.email.SendEmailUtil;
import com.chinaopensource.apiserver.common.util.encryption.EncryptionUtil;
import com.chinaopensource.apiserver.common.util.server.ServerAttributeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Enumeration;
import java.util.Objects;
import java.util.Properties;

/**
 * create by lzl ON 2017/12/03
 */
@Controller
@RequestMapping("/test")
public class TestController extends ControllerBase{

    @Autowired
    private OpenSourceConfig openSourceConfig;

    @Autowired
    private SendEmailUtil sendEmailUtil;
    @GetMapping("/get")
    public String get(){
        Properties properties = System.getProperties();
        System.out.println(properties.propertyNames());
//        Enumeration enumeration = properties.propertyNames();
//        while (enumeration.hasMoreElements()){
//            Object obj = enumeration.nextElement();
//            System.out.println(obj);
//        }
        String emailVerificationCode = EncryptionUtil.getHash("lzl:lzl1593572798", EncryptionEnum.MD5);
        StringBuilder sb = new StringBuilder();
        sb.append("<h1>来自中国开源网站激活邮件，点击链接激活。</h1>");
        sb.append("<h3><a href='http://");
        sb.append(openSourceConfig.getHostIp());
        sb.append(":");
        sb.append(ServerAttributeUtil.getPort());
        sb.append("/system/user/activation&code=");
        sb.append(emailVerificationCode);
        sb.append("'>");
        sb.append(emailVerificationCode);
        sb.append("</a>");
        sb.append("</h3></h1>");
        return sb.toString();
//        return String.valueOf(sendEmailUtil.sendEmail("2769917696","123456"));
    }

}
