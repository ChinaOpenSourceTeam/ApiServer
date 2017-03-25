package com.chinaopensource.apiserver.common.util.redis;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

@Component
public class RedisOperate {

	@Autowired
	private StringRedisTemplate template;
	

	/**
	 * 设置键
	 * @param key
	 * @param value
	 * @param expireTime
	 */
	public void  set(final String key,final String value,final long expireTime) {
		ValueOperations<String, String> ops = this.template.opsForValue();
		ops.set(key, value, expireTime, TimeUnit.MINUTES);
		
	}
	
	/**
	 * 设置键
	 * @param key
	 * @param value
	 */
	public void  set(final String key,final String value) {
		ValueOperations<String, String> ops = this.template.opsForValue();
		ops.set(key, value);
	}
	
	/**
	 * 获取值
	 * @param key
	 * @return
	 */
	public String get(final String key) {
		ValueOperations<String, String> ops = this.template.opsForValue();
		return ops.get(key);
	}
}
