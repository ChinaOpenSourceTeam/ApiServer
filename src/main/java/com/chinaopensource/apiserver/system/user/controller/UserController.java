package com.chinaopensource.apiserver.system.user.controller;

import com.chinaopensource.apiserver.common.configure.OpenSourceConfig;
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
import com.chinaopensource.apiserver.common.util.jwt.JwtTokenUtil;
import com.chinaopensource.apiserver.common.util.redis.RedisOperate;
import com.chinaopensource.apiserver.system.user.data.User;
import com.chinaopensource.apiserver.system.user.service.UserService;
import com.google.common.base.Strings;
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

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private OpenSourceConfig openSourceConfig;

	@ApiOperation(value="注册用户", notes="添加用户信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "Authorization", value = "token", required = true , dataType = "String" ,paramType = "header")
	})
    @PostMapping("/saveUser")
	public String saveUser(@Valid @RequestBody EmailAuth emailAuth,HttpServletRequest request){
//	   TODO 以后更改  session有问题
// 		1、校验图片验证是否正确
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
			return renderOk(ResponseCode.ERR_PASSWORD_LENGTH_ILLEAGL);
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
//        密码的规则： name:password 经过MD5加密
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        sb.append(":");
        sb.append(password);
//        把密码进行加密运算,保存db
		User user = new User(name,EncryptionUtil.getHash(sb.toString(), EncryptionEnum.MD5),"",
				0,"","","",email,
				UserStatusEnum.ACTIVATED,new Date(),0,
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
		if(UserStatusEnum.ACTIVATED.getStatus().equals(user.getStatus())){
			return renderOk(ResponseCode.ACCOUNT_ACTIVATION);
		}
		long minute = 1000*60;
		long hour = minute*60;
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
	public String updateUser(@Valid @RequestBody User user,HttpServletRequest request) throws BaseException{
		String token = request.getHeader(openSourceConfig.getJwtHeader());
		String loginName = jwtTokenUtil.getUsernameFromToken(token);
		if(Strings.isNullOrEmpty(loginName)){
			return renderOk(ResponseCode.ERR_SYS_PARAMETER_VALIDATE);
		}
		String phone = user.getPhone();
		if(Strings.isNullOrEmpty(phone) && Objects.isNull(userService.findByPhone(phone))){
			return renderOk(ResponseCode.PHONE_EXISTS);
		}
		User loginUser = userService.findUserByLoginName(loginName);
		if(Objects.isNull(loginUser)){
			return renderOk(ResponseCode.ERR_SYS_PARAMETER_VALIDATE);
		}
		userService.updateUser(user);
		return renderOk(ResponseCode.OK);
	}

	/**
	 * 根据token 获取用户详情
	 * @param request
	 * @return
	 */
	@ApiImplicitParams({
		@ApiImplicitParam(name = "Authorization", value = "token", required = true , dataType = "String" ,paramType = "header"),
	})
	@RequestMapping(value = "findUserById", method = RequestMethod.GET)
	public String findUser(HttpServletRequest request){
		String token = request.getHeader(openSourceConfig.getJwtHeader());
		String loginName = jwtTokenUtil.getUsernameFromToken(token);
		if(Strings.isNullOrEmpty(loginName)){
			return renderOk(ResponseCode.ERR_SYS_PARAMETER_VALIDATE);
		}
		User user = userService.findUserByLoginName(loginName);
		if(Objects.isNull(user)){
			return renderOk(ResponseCode.ERR_SYS_PARAMETER_VALIDATE);
		}
		return renderOk(ResponseCode.OK,mapOf("user",user));
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
