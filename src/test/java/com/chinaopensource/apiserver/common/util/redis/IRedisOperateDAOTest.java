package com.chinaopensource.apiserver.common.util.redis;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.chinaopensource.apiserver.ApiServerApplicationTests;

public class IRedisOperateDAOTest extends ApiServerApplicationTests{

	@Autowired
	private IRedisOperate redisOperate;

	@Test
	public void testGet() {
		redisOperate.set("test", "测试");
		Assert.assertEquals("测试", redisOperate.get("test"));
	}


}
