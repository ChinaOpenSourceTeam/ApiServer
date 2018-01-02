package com.chinaopensource.apiserver.system.login.controller;

import com.chinaopensource.apiserver.common.configure.OpenSourceConfig;
import com.chinaopensource.apiserver.common.constant.Constants;
import com.chinaopensource.apiserver.common.constant.EncryptionEnum;
import com.chinaopensource.apiserver.common.constant.ResponseCode;
import com.chinaopensource.apiserver.common.constant.UserStatusEnum;
import com.chinaopensource.apiserver.common.controller.ControllerBase;
import com.chinaopensource.apiserver.common.util.encryption.EncryptionUtil;
import com.chinaopensource.apiserver.common.util.jwt.JwtTokenUtil;
import com.chinaopensource.apiserver.common.util.redis.RedisOperate;
import com.chinaopensource.apiserver.system.login.data.LoginData;
import com.chinaopensource.apiserver.system.user.data.User;
import com.chinaopensource.apiserver.system.user.service.UserService;
import com.google.common.base.Strings;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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

	@Autowired
	private OpenSourceConfig openSourceConfig;

	@ApiOperation(value="获取token", notes="登录系统获取token值")
	@PostMapping(value = "/signIn" )
	public String signIn(@Valid @RequestBody LoginData data){
		User user = userService.findUserByLoginName(data.getLoginName());
		if(Objects.isNull(user)){
			return renderOk(ResponseCode.ERR_SYS_LOGIN_PASSWORD);
		}
		if(UserStatusEnum.UN_ACTIVATE.getStatus().equals(user.getStatus())){
			return renderOk(ResponseCode.ACCOUNT_UN_ACTIVATION);
		}
		if(!EncryptionUtil.getHash(data.getPassword(), EncryptionEnum.MD5).equals(user.getPassword())){
			return renderOk(ResponseCode.ERR_SYS_LOGIN_PASSWORD);
		}
		String token = jwtTokenUtil.generateToken(user.getLoginName());
//		redisOperate.set(data.getLoginName()+Constants.REDIS_COLON+Constants.USERINFO_TOKEN, token);
		return renderOk(ResponseCode.OK,
				mapOf("token",token,"user",userService.modifyBaseUserAttribute(user)));
	}

	@ApiOperation(value="删除token", notes="退出系统删除token")
    @GetMapping(value = "/signOut")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "Authorization", value = "token", required = true , dataType = "String" ,paramType = "header"),
	})
	public String signOut(HttpServletRequest request){
		String token = request.getHeader(openSourceConfig.getJwtHeader());
		String loginName = jwtTokenUtil.getUsernameFromToken(token);
		if(Strings.isNullOrEmpty(loginName)){
			return renderOk(ResponseCode.ERR_SYS_PARAMETER_VALIDATE);
		}
        User user = userService.findUserByLoginName(loginName);
        if(Objects.isNull(user)){
            return renderOk(ResponseCode.ERR_SYS_PARAMETER_VALIDATE);
        }
		redisOperate.deletes(loginName+Constants.REDIS_COLON+Constants.REDIS_ALL);
		return renderOk(ResponseCode.OK);
	}

}
