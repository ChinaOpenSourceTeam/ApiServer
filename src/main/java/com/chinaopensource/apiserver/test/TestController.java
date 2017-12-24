package com.chinaopensource.apiserver.test;

import com.chinaopensource.apiserver.common.configure.OpenSourceConfig;
import com.chinaopensource.apiserver.common.controller.ControllerBase;
import com.chinaopensource.apiserver.common.util.email.SendEmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        return String.valueOf(sendEmailUtil.sendEmail("2769917696","123456"));
    }

}
