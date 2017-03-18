package com.chinaopensource.apiserver.system.user.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.chinaopensource.apiserver.ApiServerApplicationTests;

public class UserServiceTest extends ApiServerApplicationTests{

	@Autowired
	private UserService userService;
	
	@Test
	public void testSave() {
	}

	@Test
	public void testDeleteUserById() {
	}

	@Test
	public void testFindUserById() {
	}

	@Test
	public void testFindAllUser() {
	}

	@Test
	public void testLoginValidate() {
		
		Assert.assertTrue(userService.loginValidate("liqiwei", "123456"));
	}

}
