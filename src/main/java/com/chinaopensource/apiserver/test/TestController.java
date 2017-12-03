package com.chinaopensource.apiserver.test;

import com.chinaopensource.apiserver.common.constant.ResponseCode;
import com.chinaopensource.apiserver.common.controller.ControllerBase;
import com.chinaopensource.apiserver.system.user.data.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * create by lzl ON 2017/12/03
 */
@Controller
@RequestMapping("/test")
public class TestController extends ControllerBase{

    @GetMapping("/get")
    public String get(){
        User user = new User();
        user.setId(12);
        user.setPassword("23423");
        user.setAddress("location");
        return renderOk(ResponseCode.OK,user);
    }
}
