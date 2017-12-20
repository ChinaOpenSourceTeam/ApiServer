package com.chinaopensource.apiserver.system.user.mapper;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;

import com.chinaopensource.apiserver.ApiServerApplicationTests;
import com.chinaopensource.apiserver.common.util.encryption.EncryptionUtil;
import com.chinaopensource.apiserver.system.user.data.User;

public class UserMapperTest extends ApiServerApplicationTests{
	
	@Autowired  
    private UserMapper mapper;  
	
	//正常保存
	@Test
	public void testSave1() {
		User user = new User();
		user.setAddress("中国安徽亳州涡阳");
		user.setAge(25);
		user.setEmail("2452403243@qq.com");
		user.setLoginName("沙沙");
		user.setPassword("123456qqwerty");
		user.setPhone("18356095840");
		user.setPhoto("c:/photo/a.jep");
	}
	/**
	 * 同名登录报异常
	 */
	@Test( expected = DuplicateKeyException.class )
	public void testSave2() {
		User user = new User();
		user.setAddress("中国安徽亳州涡阳");
		user.setAge(25);
		user.setEmail("2452403243@qq.com");
		user.setLoginName("李其伟");
		user.setPassword("123456qqwerty");
		user.setPhone("18356095840");
		user.setPhoto("c:/photo/a.jep");
	}
	
	/**
	 * 更新用户信息
	 */
	@Test
	public void testUpdate() {
		User user = new User();
		user.setAddress("中国安徽亳州涡阳");
		user.setAge(25);
		user.setEmail("2452403243@qq.com");
		user.setLoginName("李其伟1");
		user.setPhone("18356095840");
		user.setPhoto("c:/photo/a.jep");
	}
	
	/**
	 * 通过登录名查找用户
	 */
	@Test
	public void testFindUserByLoginName(){
		User user = mapper.findUserByLoginName("liqiwei");
		Assert.assertEquals("liqiwei", user.getLoginName()) ;
	}
	
	/**
	 * 通过用户ID删除
	 */
	@Test
	public void testDelete() {
		User user = mapper.findUserById(1);
		mapper.delete(1);
		user = mapper.findUserById(1);
		Assert.assertNull(user);
	}
	
	/**
	 * 通过用户Id查询用户信息
	 */
	@Test
	public void testFindUserById() {
		User user = mapper.findUserById(1);
		Assert.assertNotNull(user);
	}

	/**
	 * 查询所有用户信息
	 */
	@Test
	public void testFindAllUser() {
		List<User> userList = mapper.findAllUser();
		Assert.assertEquals(2, userList.size());
 	}
	
	/**
	 * 通过用户Id查询密码
	 */
	@Test
	public void testFindPasswordByLoginName() {
		String pwd = mapper.findPasswordByLoginName("liqiwei");
		Assert.assertEquals(EncryptionUtil.getHash("123456", "MD5"), pwd);
	}

}
