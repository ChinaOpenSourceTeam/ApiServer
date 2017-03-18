package com.chinaopensource.apiserver.system.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.chinaopensource.apiserver.common.controller.ControllerBase;
import com.chinaopensource.apiserver.system.user.data.User;
import com.chinaopensource.apiserver.system.user.data.UserList;
import com.chinaopensource.apiserver.system.user.service.UserService;

@RestController
@RequestMapping("/system/user/")
public class UserController extends ControllerBase {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "saveUser", method = RequestMethod.POST)
	public String saveUser(User user){
		userService.save(user);
		return JSON.toJSONString(rep);
	}
	
	@RequestMapping(value = "deleteUserById", method = RequestMethod.DELETE)
	public String deleteUserById(Integer id){
		userService.deleteUserById(id);
		return JSON.toJSONString(rep);
	}
	
	@RequestMapping(value = "findUserById", method = RequestMethod.GET)
	public String findUserById(Integer id){
		rep.setData(userService.findUserById(id));
		return JSON.toJSONString(rep);
	}
	@RequestMapping(value = "findAllUser", method = RequestMethod.GET)
	public String findAllUser(){
		UserList userList = new UserList();
		userList.setUserList(userService.findAllUser());
		rep.setData(userList);
		return JSON.toJSONString(rep);
	}
}
