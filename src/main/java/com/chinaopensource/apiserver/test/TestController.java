package com.chinaopensource.apiserver.test;

import com.chinaopensource.apiserver.common.configure.OpenSourceConfig;
import com.chinaopensource.apiserver.common.constant.ResponseCode;
import com.chinaopensource.apiserver.common.controller.ControllerBase;
import com.chinaopensource.apiserver.common.util.image.PictureGeneratorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * create by lzl ON 2017/12/03
 */
@Controller
@RequestMapping("/test")
public class TestController extends ControllerBase{

    @Autowired
    private OpenSourceConfig openSourceConfig;

    @GetMapping("/get")
    public String get(){
        System.out.println(openSourceConfig.getJwtHeader());
        System.out.println(openSourceConfig.getImageWidth());
        System.out.println(openSourceConfig.getEmailContentPattern());
        return renderOk(ResponseCode.OK, openSourceConfig.getEmailContentPattern()
                +" "+openSourceConfig.getJwtHeader()
                +" "+openSourceConfig.getImageWidth());
    }
    
}
