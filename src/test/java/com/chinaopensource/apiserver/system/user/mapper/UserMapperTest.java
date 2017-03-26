package com.chinaopensource.apiserver.system.user.mapper;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.chinaopensource.apiserver.ApiServerApplicationTests;
import com.chinaopensource.apiserver.system.user.data.User;

public class UserMapperTest extends ApiServerApplicationTests{
	
	@Autowired  
    private UserMapper mapper;  
	
	@Test
	public void testSave() {
		User user = new User();
		user.setAddress("中国安徽亳州涡阳");
		user.setAge(25);
		user.setEmail("2452403243@qq.com");
		user.setLoginName("李其伟");
		user.setPassword("123456qqwerty");
		user.setPhone("18356095840");
		user.setPhoto("c:/photo/a.jep");
		user.setUserName("沙沙");
		
		mapper.save(user);
	}
	
	@Test
	public void testFindUserByLoginName(){
		User user = mapper.findUserByLoginName("liqiwei");
		Assert.assertEquals("liqiwei", user.getLoginName()) ;
		
	}
	
}
