package com.chinaopensource.apiserver.system.user.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.chinaopensource.apiserver.common.constant.Constants;
import com.chinaopensource.apiserver.common.constant.ErrorCode;
import com.chinaopensource.apiserver.common.constant.ErrorMessage;
import com.chinaopensource.apiserver.common.controller.ControllerBase;
import com.chinaopensource.apiserver.common.controller.ResponseBase;
import com.chinaopensource.apiserver.common.exception.BaseException;
import com.chinaopensource.apiserver.common.util.BeanMapTransformation;
import com.chinaopensource.apiserver.common.util.redis.RedisOperate;
import com.chinaopensource.apiserver.system.user.data.BaseUser;
import com.chinaopensource.apiserver.system.user.data.User;
import com.chinaopensource.apiserver.system.user.data.UserList;
import com.chinaopensource.apiserver.system.user.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/system/user/")
@Api(description = "用户管理")
public class UserController extends ControllerBase {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RedisOperate redisOperate;
	
	@ApiOperation(value="保存用户信息", notes="添加用户信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "Authorization", value = "token", required = true , dataType = "String" ,paramType = "header")
	})
	@RequestMapping(value = "saveUser", method = RequestMethod.POST)
	public String saveUser(@Valid @RequestBody User user) throws BaseException{
		userService.save(user);
		rep=new ResponseBase(ErrorCode.OK, ErrorMessage.getMessage(ErrorCode.OK));
		return JSON.toJSONString(rep);
	}
	
	@ApiOperation(value="修改用户信息", notes="修改用户信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "Authorization", value = "token", required = true , dataType = "String" ,paramType = "header")
	})
	@RequestMapping(value = "updateUser", method = RequestMethod.PUT)
	//TODO 分组验证
	public String updateUser(@Valid @RequestBody BaseUser user) throws BaseException{
		userService.update(user);
		rep=new ResponseBase(ErrorCode.OK, ErrorMessage.getMessage(ErrorCode.OK));
		return JSON.toJSONString(rep);
	}

	@ApiOperation(value="删除用户信息", notes="删除用户信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "Authorization", value = "token", required = true , dataType = "String" ,paramType = "header"),
		@ApiImplicitParam(name = "id", value = "用户Id", required = true , dataType = "Integer" ,paramType = "query")
	})
	@RequestMapping(value = "deleteUserById", method = RequestMethod.DELETE)
	public String deleteUserById(Integer id){
		userService.deleteUserById(id);
		rep=new ResponseBase(ErrorCode.OK, ErrorMessage.getMessage(ErrorCode.OK));
		return JSON.toJSONString(rep);
	}
	
	@ApiOperation(value="通过ID用户信息", notes="通过ID用户信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "Authorization", value = "token", required = true , dataType = "String" ,paramType = "header"),
		@ApiImplicitParam(name = "id", value = "用户Id", required = true , dataType = "Integer" ,paramType = "query")
	})
	@RequestMapping(value = "findUserById", method = RequestMethod.GET)
	public String findUserById(Integer id){
		rep=new ResponseBase(ErrorCode.OK, ErrorMessage.getMessage(ErrorCode.OK));
		rep.setData(userService.findUserById(id));
		return JSON.toJSONString(rep);
	}
	
	@ApiOperation(value="通过登录名获取用户信息", notes="通过登录名获取用户信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "Authorization", value = "token", required = true , dataType = "String" ,paramType = "header"),
		@ApiImplicitParam(name = "loginName", value = "登录名", required = true , dataType = "String" ,paramType = "query")
	})
	@RequestMapping(value = "findUserByLoginName", method = RequestMethod.GET)
	public String findUserByLoginName(String loginName){
		rep=new ResponseBase(ErrorCode.OK, ErrorMessage.getMessage(ErrorCode.OK));
		BaseUser user = userService.findUserByLoginName(loginName);
		redisOperate.setMap(user.getLoginName()+Constants.REDIS_COLON+Constants.USERINFO_INFO, BeanMapTransformation.transBeanToMap(user, null));
		rep.setData(user);
		return JSON.toJSONString(rep);
	}
	
	@ApiOperation(value="查找所有用户", notes="查找所有用户信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "Authorization", value = "token", required = true , dataType = "String" ,paramType = "header")
	})
	@RequestMapping(value = "findAllUser", method = RequestMethod.GET)
	public String findAllUser(){
		UserList userList = new UserList();
		userList.setUserList(userService.findAllUser());
		rep=new ResponseBase(ErrorCode.OK, ErrorMessage.getMessage(ErrorCode.OK));
		rep.setData(userList);
		return JSON.toJSONString(rep);
	}
}
