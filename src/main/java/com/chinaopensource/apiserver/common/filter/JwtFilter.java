package com.chinaopensource.apiserver.common.filter;

import com.alibaba.fastjson.JSON;
import com.chinaopensource.apiserver.common.configure.OpenSourceConfig;
import com.chinaopensource.apiserver.common.constant.Constants;
import com.chinaopensource.apiserver.common.constant.ResponseCode;
import com.chinaopensource.apiserver.common.controller.ResponseBase;
import com.chinaopensource.apiserver.common.util.jwt.JwtTokenUtil;
import com.chinaopensource.apiserver.common.util.redis.RedisOperate;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * 验证 接口token
 * 
 * @author lqw
 * 2017年4月8日 下午9:46:03
 */
//@WebFilter(filterName="jwtFilter",value="/*")
public class JwtFilter implements Filter {
    
	@Autowired
    private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private RedisOperate redisOperate;

	@Autowired
	private OpenSourceConfig openSourceConfig;

    /**
     * 封装，不需要过滤的list列表
     */
    protected static List<String> urls = new ArrayList<String>();

    // 添加过滤条件,过滤掉不需要token的请求
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    	urls.add("system/login/signIn");
    	urls.add("favicon.ico");
    	// 排除swagger过滤
    	urls.add("swagger-ui.html");
    	urls.add("v2/api-docs");
    	urls.add("test/get");
    	urls.add("test/authCode");
    	urls.add("system/login/send");
    	urls.add("system/user/saveUser");
    	urls.add("system/identifyingCode");
    	urls.add("node/findNodeById");
    	urls.add("node/findNodesByNodeName");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        String url = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
        if (url.startsWith("/") && url.length() > 1) {
            url = url.substring(1);
        }
        // 排查swagger判断
        if (urls.contains(url)||url.indexOf("swagger")!=-1){
            chain.doFilter(httpRequest, httpResponse);
            return;
        } else {
        	 String token = httpRequest.getHeader(openSourceConfig.getJwtHeader());
//        	 token 不存在或者校验不通过
        	 if((token == null) || !jwtTokenUtil.validateToken(token)){
                 httpResponse.setCharacterEncoding("UTF-8");
                 httpResponse.setContentType("application/json; charset=utf-8");
                 PrintWriter out = httpResponse.getWriter();
                 ResponseBase rep = new ResponseBase(ResponseCode.ERR_SYS_TOKEN_NONE);
                 out.append(JSON.toJSONString(rep));
                 return;
             } else {
                 StringBuilder key = new StringBuilder();
                 key.append(Constants.TOKEN);
                 key.append(Constants.REDIS_COLON);
                 key.append(jwtTokenUtil.getUsernameFromToken(token));
        		 // 刷新token值
        		 redisOperate.set(key.toString(), jwtTokenUtil.refreshToken(token));
        		 // TODO 请求的日志记录  用户是否存在  请求的接口的权限
        		 chain.doFilter(httpRequest, httpResponse);
        	 }
        	 
        }

    }

    @Override
    public void destroy() {

    }


}