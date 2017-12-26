package com.chinaopensource.apiserver.test;

import com.alibaba.fastjson.JSON;
import com.chinaopensource.apiserver.common.configure.OpenSourceConfig;
import com.chinaopensource.apiserver.common.controller.ControllerBase;
import com.chinaopensource.apiserver.common.util.email.SendEmailUtil;
import com.chinaopensource.apiserver.system.user.data.User;
import com.chinaopensource.apiserver.system.user.service.UserService;
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

    @Autowired
    private UserService userService;

    @GetMapping("/get")
    public String get(){
        User user = userService.findUserById(1);
        return JSON.toJSONString(user);
    }

}
