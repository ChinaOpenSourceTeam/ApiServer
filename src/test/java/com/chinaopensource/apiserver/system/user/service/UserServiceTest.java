package com.chinaopensource.apiserver.system.user.service;

import com.chinaopensource.apiserver.common.constant.EncryptionEnum;
import com.chinaopensource.apiserver.common.util.encryption.EncryptionUtil;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.chinaopensource.apiserver.ApiServerApplicationTests;
import com.chinaopensource.apiserver.common.exception.BaseException;
import com.chinaopensource.apiserver.system.user.data.User;

public class UserServiceTest extends ApiServerApplicationTests{

	@Autowired
	private UserService userService;
	
	//保存用户信息  添加用户
	@Test
	public void testSave() throws BaseException {
		User user = new User();
		user.setAddress("中国");
		user.setAge(25);
		user.setEmail("2452403243@qq.com");
		user.setLoginName("沙沙");
		user.setPassword("123321");
		user.setPhone("18356095840");
		user.setPhoto("c:/photo/a.jep");
//		user.setUserName("李沙沙");
		this.userService.save(user);
	}
	

	// 通过用户ID删除用户信息
	@Test
	public void testDeleteUserById() {
		Assert.assertNotNull(this.userService.findUserById(1));
		this.userService.deleteUserById(1);
		Assert.assertNull(this.userService.findUserById(1));
	}

	// 通过用户ID查找用户
	@Test
	public void testFindUserById() {
		Assert.assertEquals("liqiwei", this.userService.findUserById(1).getLoginName());
	}

	// 查找所有用户
	@Test
	public void testFindAllUser() {
		Assert.assertEquals(2, this.userService.findAllUser().size());
	}

	// 登录人密码验证
	@Test
	public void testLoginValidate() {

		System.out.println( userService.findUserByLoginName("liqiwei").getPassword());

		System.out.println(EncryptionUtil.getHash("123456", EncryptionEnum.MD5));
	//	Assert.assertTrue(userService.loginValidate("liqiwei", "123456"));
	}

}
