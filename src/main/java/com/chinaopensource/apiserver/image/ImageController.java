package com.chinaopensource.apiserver.image;

import com.chinaopensource.apiserver.common.configure.OpenSourceConfig;
import com.chinaopensource.apiserver.common.util.image.PictureGeneratorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * create by lzl ON 2017/12/24
 */
@Controller
@RequestMapping("/system")
public class ImageController {

    @Autowired
    private OpenSourceConfig openSourceConfig;

    @GetMapping("/identifyingCode")
    public void generateImage(HttpServletRequest request, HttpServletResponse response) throws Exception{
        PictureGeneratorUtil.generatorImage(request,response,openSourceConfig.getImageWidth(),openSourceConfig.getImageHeight());
    }

}
