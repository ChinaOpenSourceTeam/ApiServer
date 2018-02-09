package com.chinaopensource.apiserver.system.user.controller;

import com.chinaopensource.apiserver.blog.data.Blog;
import com.chinaopensource.apiserver.common.configure.OpenSourceConfig;
import com.chinaopensource.apiserver.common.constant.EncryptionEnum;
import com.chinaopensource.apiserver.common.constant.ResponseCode;
import com.chinaopensource.apiserver.common.constant.UserStatusEnum;
import com.chinaopensource.apiserver.common.controller.ControllerBase;
import com.chinaopensource.apiserver.common.util.email.EmailAuth;
import com.chinaopensource.apiserver.common.util.email.SendEmailUtil;
import com.chinaopensource.apiserver.common.util.encryption.EncryptionUtil;
import com.chinaopensource.apiserver.common.util.jwt.JwtTokenUtil;
import com.chinaopensource.apiserver.common.util.redis.RedisOperate;
import com.chinaopensource.apiserver.node.service.NodeService;
import com.chinaopensource.apiserver.system.user.data.User;
import com.chinaopensource.apiserver.system.user.service.UserService;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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

	@Autowired
	private NodeService nodeService;

	/**
	 *  ApiOperation
	 * @param emailAuth
	 * @param request
	 * @return
	 */
	@ApiOperation(value="注册用户", notes="添加用户信息")
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
	@PostMapping(value = "updateUser")
	//TODO 分组验证
	public String updateUser(@RequestParam(name = "age")Integer age,
							 @RequestParam(name = "phone")String phone,
							 @RequestParam(name = "email")String email,
							 @RequestParam(name = "address")String address,
							 @RequestParam(name = "nickName")String nickName,
							 HttpServletRequest request) {
		String token = request.getHeader(openSourceConfig.getJwtHeader());
		String loginName = jwtTokenUtil.getUsernameFromToken(token);
		if(Strings.isNullOrEmpty(loginName)){
			return renderOk(ResponseCode.ERR_SYS_PARAMETER_VALIDATE);
		}
		User user = userService.findUserByLoginName(loginName);
		if( user == null){
			return renderOk(ResponseCode.ERR_SYS_PARAMETER_VALIDATE);
		}
		if(!Strings.isNullOrEmpty(email)){
			if(userService.checkEmail(email)){
				if(userService.findUserByEmail(email) != null){
					return renderOk(ResponseCode.EMAIL_EXITS);
				}
			}else {
				return renderOk(ResponseCode.ERR_EMAIL_ILLEGAL);
			}
		}
		if(!Strings.isNullOrEmpty(phone)){
			if(userService.findByPhone(phone) != null){
				return renderOk(ResponseCode.PHONE_EXISTS);
			}
		}
		userService.updateUserByPara(age,phone,email,address,nickName,user.getId());
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
	@GetMapping(value = "findUserById")
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
//	 ApiImplicitParams 接口验证token使用的
	@ApiImplicitParams({
		@ApiImplicitParam(name = "Authorization", value = "token", required = true , dataType = "String" ,paramType = "header")
	})
	@GetMapping(value = "findAllUser")
	public String findAllUser(){
		return renderOk(ResponseCode.OK,mapOf("allUser",userService.findAllUser()));
	}

	/**
	 * 根据博客所属类型 分页获取用户已创建的博客
	 * @param request
	 * @param type
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@ApiOperation(value = "获取用户创建的blog",notes = "获取用户创建的blog")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Authorization", value = "token", required = true , dataType = "String" ,paramType = "header"),
			@ApiImplicitParam(name = "pageNum", value = "页数", dataType = "Integer" ,paramType = "query"),
			@ApiImplicitParam(name = "pageSize", value = "页大小", dataType = "Integer" ,paramType = "query")
	})
	@GetMapping("/getBlog")
	public String getComment(
			HttpServletRequest request,
			@RequestParam(name = "type",defaultValue = "0")Integer type,
			@RequestParam(name ="pageNum" ,defaultValue = "1") Integer pageNum,
			@RequestParam(name = "pageSize",defaultValue = "10") Integer pageSize){
		if(type == 0){
//			表明用户当前未创建blogType
			return renderOk();
		}
		String token = request.getHeader(openSourceConfig.getJwtHeader());
		String loginName = jwtTokenUtil.getUsernameFromToken(token);
		if(Strings.isNullOrEmpty(loginName)){
			return renderOk(ResponseCode.ERR_SYS_PARAMETER_VALIDATE);
		}
		User user = userService.findUserByLoginName(loginName);
		if(Objects.isNull(user)){
			return renderOk(ResponseCode.ERR_SYS_PARAMETER_VALIDATE);
		}
		PageInfo<Blog> page = userService.findBlogListByPage(pageNum,pageSize,user.getId(),type);
		return renderOk(ResponseCode.OK,mapOf("page_data",page));
	}

	@ApiOperation(value = "获取用户创建bolg分类",notes = "获取用户创建bolg分类")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Authorization", value = "token", required = true , dataType = "String" ,paramType = "header"),
	})
	@GetMapping("/getBlogType")
	public String getBlogType(HttpServletRequest request){
		String token = request.getHeader(openSourceConfig.getJwtHeader());
		String loginName = jwtTokenUtil.getUsernameFromToken(token);
		if(Strings.isNullOrEmpty(loginName)){
			return renderOk(ResponseCode.ERR_SYS_PARAMETER_VALIDATE);
		}
		User user = userService.findUserByLoginName(loginName);
		if(Objects.isNull(user)){
			return renderOk(ResponseCode.ERR_SYS_PARAMETER_VALIDATE);
		}
		return renderOk(ResponseCode.OK,mapOf("blog_type",nodeService.findListByUserIdAndDelete(user.getId(),false)));
	}

}
