package com.chinaopensource.apiserver.system.user.controller;

import com.chinaopensource.apiserver.common.constant.Constants;
import com.chinaopensource.apiserver.common.constant.EncryptionEnum;
import com.chinaopensource.apiserver.common.constant.ResponseCode;
import com.chinaopensource.apiserver.common.constant.UserStatusEnum;
import com.chinaopensource.apiserver.common.controller.ControllerBase;
import com.chinaopensource.apiserver.common.exception.BaseException;
import com.chinaopensource.apiserver.common.util.BeanMapTransformation;
import com.chinaopensource.apiserver.common.util.email.EmailAuth;
import com.chinaopensource.apiserver.common.util.email.SendEmailUtil;
import com.chinaopensource.apiserver.common.util.encryption.EncryptionUtil;
import com.chinaopensource.apiserver.common.util.redis.RedisOperate;
import com.chinaopensource.apiserver.system.user.data.User;
import com.chinaopensource.apiserver.system.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Date;
import java.util.Objects;

@RestController
@RequestMapping("/system/user")
@Api(description = "用户管理")
public class UserController extends ControllerBase {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RedisOperate redisOperate;

	@Autowired
	private SendEmailUtil sendEmailUtil;

	@ApiOperation(value="注册用户", notes="添加用户信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "Authorization", value = "token", required = true , dataType = "String" ,paramType = "header")
	})
    @PostMapping("/saveUser")
	public String saveUser(@Valid @RequestBody EmailAuth emailAuth,HttpServletRequest request){
//	    1、校验图片验证是否正确
//		String sessionVerificationCode = (String) request.getSession().getAttribute(Constants.SESSION_PICTURE);
//        if(!emailAuth.getImageVerificationCode().equals(sessionVerificationCode)){
//            return renderOk(ResponseCode.ERR_VIRIFICATIOIN);
//        }
        String name = emailAuth.getName();
        String email = emailAuth.getEmail();
        String password = emailAuth.getPasswd();
        int nameLengthMax = 18;
        int nameLengthMin = 6;
        int passwordLengthMin = 6;
        int passwordLengthMax = 10;
//        检查长度
        if(name.length() > nameLengthMax || name.length() < nameLengthMin){
			return renderOk(ResponseCode.ERR_LOGIN_NAME_LENGTH);
		}
		if(password.length() > passwordLengthMax || password.length() < passwordLengthMin){
			return renderOk(ResponseCode.ERR_PASSWORD_LENGTH_ILLAGEL);
		}
		if(!userService.checkPasswordContent(password)){
			return renderOk(ResponseCode.ERR_PASSWORD_CONTENT_ILLEGAL);
		}
//        对用户名进行校验
		if(!userService.checkLoginNameStartWith(name)){
			return renderOk(ResponseCode.ERR_LOGIN_NAME_START_WITH);
		}
//		检查loginName内容
		if(!userService.checkLoginNameContent(name)){
			return renderOk(ResponseCode.ERR_LOGIN_NAME_ILLEGAL);
		}
//		 对邮箱进行规则校验
		if(!userService.checkEmail(email)){
			return renderOk(ResponseCode.ERR_EMAIL_ILLEGAL);
		}
//       校验用户名是否存在
        if(!userService.existsByLoginName(name)){
            return renderOk(ResponseCode.ACCOUNT_EXISTS);
        }
//       校验邮箱是否存在
        if(!userService.existsBYEmail(email)){
            return renderOk(ResponseCode.EMAIL_EXITS);
        }
//        把密码进行加密运算,保存db
		User user = new User(name,EncryptionUtil.getHash(password, EncryptionEnum.MD5),"",
				0,"","","",email,
				UserStatusEnum.UN_ACTIVATE,new Date(),0,
				userService.getEmailVerificationCode(name));
		userService.save(user);
//		发送邮箱激活验证码
//		sendEmailUtil.sendEmail(email,user.getVerificationCode());
		return renderOk(ResponseCode.OK);
	}

	/**
	 * 激活用户
	 * @return
	 */
	@GetMapping("activation")
	public String activationUser(@RequestParam("code") String code){
//		验证的长度
		int codeLength = 32;
		if(code.length() != 32){
			return renderOk(ResponseCode.ERR_ACTIVATION_CODE_LENGTH);
		}
		User user = userService.findUserByVerificationCode(code);
		if(Objects.isNull(user)){
//			验证码错误
			return renderOk(ResponseCode.ERR_VIRIFICATIOIN);
		}
		long minute = 1000*60;
		long hour = minute*60*60;
		long day = hour*24;
		Date now = new Date();
		long time = now.getTime()-user.getCreateTime().getTime();
		long minuteTime =  time%day%hour/minute;
		if( minuteTime > 30L){
//			两个时间相差30分钟，超过了验证码的有效期,激活码失效
			return renderOk(ResponseCode.ERR_OUT_OF_VALIDITY);
		}
		if(userService.updateStatus(user.getId(), UserStatusEnum.ACTIVATED)){
//			激活成功
			return renderOk(ResponseCode.VIRIFICATION_SUCCESS);
		}else {
//			激活失败
			return renderOk(ResponseCode.VIRIFICATION_ERROR);
		}
	}
	
	@ApiOperation(value="修改用户信息", notes="修改用户信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "Authorization", value = "token", required = true , dataType = "String" ,paramType = "header")
	})
	@RequestMapping(value = "updateUser", method = RequestMethod.PUT)
	//TODO 分组验证
	public String updateUser(@Valid @RequestBody User user) throws BaseException{
//		userService.update(user);
//		更新用户信息 后期再sql中石油表达式进行优化
		return renderOk(ResponseCode.OK);
	}

	@ApiOperation(value="删除用户信息", notes="删除用户信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "Authorization", value = "token", required = true , dataType = "String" ,paramType = "header"),
		@ApiImplicitParam(name = "id", value = "用户Id", required = true , dataType = "Integer" ,paramType = "query")
	})
	@RequestMapping(value = "deleteUserById", method = RequestMethod.DELETE)
	public String deleteUserById(Integer id){
		userService.deleteUserById(id);
		return renderOk(ResponseCode.OK);
	}
	
	@ApiOperation(value="通过ID用户信息", notes="通过ID用户信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "Authorization", value = "token", required = true , dataType = "String" ,paramType = "header"),
		@ApiImplicitParam(name = "id", value = "用户Id", required = true , dataType = "Integer" ,paramType = "query")
	})
	@RequestMapping(value = "findUserById", method = RequestMethod.GET)
	public String findUserById(Integer id){
		return renderOk(ResponseCode.OK,userService.findUserById(id));
	}
	
	@ApiOperation(value="通过登录名获取用户信息", notes="通过登录名获取用户信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "Authorization", value = "token", required = true , dataType = "String" ,paramType = "header"),
		@ApiImplicitParam(name = "loginName", value = "登录名", required = true , dataType = "String" ,paramType = "query")
	})
	@RequestMapping(value = "findUserByLoginName", method = RequestMethod.GET)
	public String findUserByLoginName(String loginName){
		User user = userService.findUserByLoginName(loginName);
		redisOperate.setMap(user.getLoginName()+Constants.REDIS_COLON+Constants.USERINFO_INFO, BeanMapTransformation.transBeanToMap(user, null));
		return renderOk(ResponseCode.OK,user);
	}
	
	@ApiOperation(value="查找所有用户", notes="查找所有用户信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "Authorization", value = "token", required = true , dataType = "String" ,paramType = "header")
	})
	@RequestMapping(value = "findAllUser", method = RequestMethod.GET)
	public String findAllUser(){
		return renderOk(ResponseCode.OK,mapOf("allUser",userService.findAllUser()));
	}

}
