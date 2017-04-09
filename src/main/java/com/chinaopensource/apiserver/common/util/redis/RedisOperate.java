package com.chinaopensource.apiserver.common.util.redis;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

/**
 * redis 的操作工具类
 * 
 * @author lqw
 * 2017年4月8日 下午10:04:01
 */
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
	 * 设置键
	 * @param key
	 * @param value
	 */
	public void  setMap(final String key,final Map<?, ?> value) {
		HashOperations<String, Object, Object>  ops = this.template.opsForHash();
		ops.putAll(key, value);
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
	
	/**
	 * 删除值
	 * @param key
	 * @return
	 */
	public void delete(final String key){
//		ValueOperations<String, String> ops = this.template.opsForValue();
//		ops.getOperations().delete(key);
		this.template.delete(key);
	}
}
