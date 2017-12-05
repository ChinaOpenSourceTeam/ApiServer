package com.chinaopensource.apiserver.system.login.controller;

import com.chinaopensource.apiserver.common.constant.Constants;
import com.chinaopensource.apiserver.common.constant.ResponseCode;
import com.chinaopensource.apiserver.common.controller.ControllerBase;
import com.chinaopensource.apiserver.common.util.jwt.JwtTokenUtil;
import com.chinaopensource.apiserver.common.util.redis.RedisOperate;
import com.chinaopensource.apiserver.system.login.data.LoginData;
import com.chinaopensource.apiserver.system.login.data.Token;
import com.chinaopensource.apiserver.system.user.data.BaseUser;
import com.chinaopensource.apiserver.system.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.Objects;

/**
 * 用户登录相关操作
 * 
 * @author lqw
 * 2017年4月7日 下午10:30:16
 */
@RestController
@RequestMapping("/system/login")
@Api(description = "登录管理")
public class LoginController extends ControllerBase{

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
    private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RedisOperate redisOperate;
	
	@ApiOperation(value="获取token", notes="登录系统获取token值")
	@PostMapping(value = "/signIn" )
	public String signIn(@Valid @RequestBody LoginData data){
		if(userService.loginValidate(data.getLoginName(), data.getPassword())){
			Token token = new Token();
			token.setToken(jwtTokenUtil.generateToken(data.getLoginName()));
			// 保存token值到redis
			redisOperate.set(data.getLoginName()+Constants.REDIS_COLON+Constants.USERINFO_TOKEN, token.getToken());
			return renderOk(ResponseCode.OK,token);
		}else{
			return renderError(ResponseCode.ERR_SYS_LOGIN_PASSWORD);
		}
	}
	
	@ApiOperation(value="删除token", notes="退出系统删除token")
	@RequestMapping(value = "signOut", method = RequestMethod.GET)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "Authorization", value = "token", required = true , dataType = "String" ,paramType = "header"),
		@ApiImplicitParam(name = "loginName", value = "登录名", required = true , dataType = "String" ,paramType = "query")
	})
	public String signOut(@Min(6) String loginName){
        BaseUser user = userService.findUserByLoginName(loginName);
	    if(Objects.isNull(user)){
            return renderError(ResponseCode.ERR_SYS_PARAMETER_VALIDATE);
        }
		redisOperate.deletes(loginName+Constants.REDIS_COLON+Constants.REDIS_ALL);
		return renderOk();
	}
	
}
