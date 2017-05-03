package com.chinaopensource.apiserver.common.util.redis;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.chinaopensource.apiserver.ApiServerApplicationTests;


public class RedisOperateTest extends ApiServerApplicationTests{
	
	@Autowired
	private RedisOperate redisOperate;

	@Test
	public void testSetStringStringLong() {
		redisOperate.set("I", "我",3);
	}

	@Test
	public void testSetStringString() {
		redisOperate.set("name", "姓名");
	}

	@Test
	public void testGet() {
		Assert.assertEquals("姓名", redisOperate.get("name"));
	}
	
	@Test
	public void testDelete() {
		redisOperate.deletes("liqiwei:*");
	}

}
