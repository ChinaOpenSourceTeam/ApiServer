package com.chinaopensource.apiserver.system.login.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.chinaopensource.apiserver.common.controller.ControllerBase;
import com.chinaopensource.apiserver.common.util.jwt.JwtTokenUtil;
import com.chinaopensource.apiserver.system.login.data.Token;


@RestController
@RequestMapping("/system/login/")
public class LoginController extends ControllerBase{

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
    private JwtTokenUtil jwtTokenUtil;
	
	@RequestMapping(value = "signIn", method = RequestMethod.GET)
	public String signIn(String username , String password){
		LOGGER.info("username:{},password:{}",username,password);
		Token token = new Token();
		token.setToken(jwtTokenUtil.generateToken(username));
		rep.setData(token);
		return JSON.toJSONString(rep);
	}
	
	@RequestMapping(value = "signOut", method = RequestMethod.GET)
	public String signOut(String username , String password){
		LOGGER.info("username:{},password:{}",username,password);
		String token = jwtTokenUtil.generateToken(username);
		System.out.println(jwtTokenUtil.getUsernameFromToken(token));
		
		return "username:"+username+"\npassword:"+password+"\t"+token;
	}
	
}
