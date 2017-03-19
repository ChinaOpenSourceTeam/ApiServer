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
import com.chinaopensource.apiserver.common.constant.ErrorCode;
import com.chinaopensource.apiserver.common.constant.ErrorMessage;
import com.chinaopensource.apiserver.common.controller.ResponseBase;
import com.chinaopensource.apiserver.common.util.jwt.JwtTokenUtil;


@WebFilter(filterName="jwtFilter",value="/*")
public class JwtFilter implements Filter {
    
	@Autowired
    private JwtTokenUtil jwtTokenUtil;
	
    /**
     * 封装，不需要过滤的list列表
     */
    protected static List<String> urls = new ArrayList<String>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    	urls.add("system/login/signIn");
    	urls.add("favicon.ico");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        System.out.println(httpRequest.getRequestURI());
        String url = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
        if (url.startsWith("/") && url.length() > 1) {
            url = url.substring(1);
        }
        if (urls.contains(url)){
            chain.doFilter(httpRequest, httpResponse);
            return;
        } else {
        	 String token = httpRequest.getHeader("Authorization");
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
        		 chain.doFilter(httpRequest, httpResponse);
        	 }
        	 
        }

    }

    @Override
    public void destroy() {

    }


}