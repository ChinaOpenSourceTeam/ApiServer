package com.chinaopensource.apiserver.system.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.chinaopensource.apiserver.common.constant.ErrorCode;
import com.chinaopensource.apiserver.common.constant.ErrorMessage;
import com.chinaopensource.apiserver.common.controller.ControllerBase;
import com.chinaopensource.apiserver.common.controller.ResponseBase;
import com.chinaopensource.apiserver.common.util.jwt.JwtTokenUtil;
import com.chinaopensource.apiserver.common.util.redis.RedisOperate;
import com.chinaopensource.apiserver.system.login.data.Token;
import com.chinaopensource.apiserver.system.user.service.UserService;


@RestController
@RequestMapping("/system/login/")
public class LoginController extends ControllerBase{

	@Autowired
    private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RedisOperate redisOperate;
	
	@RequestMapping(value = "signIn", method = RequestMethod.GET)
	public String signIn(String username , String password){
		if(userService.loginValidate(username, password)){
			Token token = new Token();
			token.setToken(jwtTokenUtil.generateToken(username));
			rep=new ResponseBase(ErrorCode.OK, ErrorMessage.getMessage(ErrorCode.OK));
			rep.setData(token);
			//TODO  没有解决服务器redis无法获取连接池  本地可以
			redisOperate.set(username+":token", token.getToken());
		}else{
			rep=new ResponseBase(ErrorCode.ERR_SYS_LOGIN_PASSWORD,ErrorMessage.getMessage(ErrorCode.ERR_SYS_LOGIN_PASSWORD));
		}
		return JSON.toJSONString(rep);
	}
	
	@RequestMapping(value = "signOut", method = RequestMethod.GET)
	public String signOut(String username , String password){
		String token = jwtTokenUtil.generateToken(username);
		System.out.println(jwtTokenUtil.getUsernameFromToken(token));
		return "username:"+username+"\npassword:"+password+"\t"+token;
	}
	
}
