package com.chinaopensource.apiserver.common.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.chinaopensource.apiserver.common.constant.Constants;
import com.chinaopensource.apiserver.common.constant.ErrorCode;
import com.chinaopensource.apiserver.common.constant.ErrorMessage;
import com.chinaopensource.apiserver.common.controller.ResponseBase;
import com.chinaopensource.apiserver.common.util.jwt.JwtTokenUtil;
import com.chinaopensource.apiserver.common.util.redis.RedisOperate;


@WebFilter(filterName="jwtFilter",value="/*")
public class JwtFilter implements Filter {
    
	@Autowired
    private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private RedisOperate redisOperate;
	
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
        	 String token = httpRequest.getHeader(Constants.JWT_HEADER);
        	 String name = httpRequest.getHeader("loginName");
        	 if(token==null){
        		 httpResponse.setCharacterEncoding("UTF-8");  
            	 httpResponse.setContentType("application/json; charset=utf-8");  
            	 PrintWriter out = httpResponse.getWriter();  
            	 ResponseBase rep = new ResponseBase(ErrorCode.ERR_SYS_TOKEN_NONE, ErrorMessage.getMessage(ErrorCode.ERR_SYS_TOKEN_NONE));
            	 out.append(JSON.toJSONString(rep)); 
            	 return;
        	 } else if(!(jwtTokenUtil.validateToken(token, name))){
        		 httpResponse.setCharacterEncoding("UTF-8");  
            	 httpResponse.setContentType("application/json; charset=utf-8");  
            	 PrintWriter out = httpResponse.getWriter();  
            	 ResponseBase rep = new ResponseBase(ErrorCode.ERR_SYS_TOKEN_INVALID, ErrorMessage.getMessage(ErrorCode.ERR_SYS_TOKEN_INVALID));
            	 out.append(JSON.toJSONString(rep)); 
            	 return;
        	 } else {
        		 // 刷新token值
        		 redisOperate.set(name+Constants.REDIS_COLON+Constants.USERINFO_TOKEN, jwtTokenUtil.refreshToken(token));
        		 // TODO 请求的日志记录  用户是否存在  请求的接口的权限
        		 chain.doFilter(httpRequest, httpResponse);
        	 }
        	 
        }

    }

    @Override
    public void destroy() {

    }


}