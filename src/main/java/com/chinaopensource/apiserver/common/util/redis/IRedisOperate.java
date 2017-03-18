package com.chinaopensource.apiserver.common.util.redis;

import java.util.Map;
import java.util.Set;

public interface IRedisOperate {
    
    /**
     * 
    * @Title: hkeys 
    * @author lqw
    * @Description: 获取hash集合中的所有key 
     */
    Set<String> hkeys(final String key);
    
    void hMset(final String key,final Map<String, String> map,final long expireTime);
    
    /**
     * 
    * @Title: sismember 
    * @author lqw
    * @Description: 判断set集合中是否存在mem内容
     */
    boolean sismember(final String key,final String mem);
    
    void srem(final String key,final String mem);
    /**
     * 
    * @Title: scard 
    * @author lqw
    * @Description: 获取Set集合中的总数 
     */
    long scard(final String key);
    
    /**
     * 
    * @Title: keys 
    * @author lqw
    * @Description: 模糊查询所有匹配patten的key
     */
    Set<String> keys(final String patten);
    
    /**
     * 向redis添加hash类型数据
     * @param key
     * @param map
     */
    void hMset(final String key,final Map<String, String> map);
    
    /**
     * 从redis获取hash数据
     * @param key
     * @return
     */
    Map<String,String> hGetAll(final String key);
    
    /**
     * 
    * @Title: hset 
    * @author lqw
    * @Description: 色环之定key中的hkey的value
     */
    Boolean hset(final String key,final String hkey,final String hval);
    
    /**
     * 
    * @Title: hget 
    * @author lqw
    * @Description: 获取指定key中的hkey的value
    * @throws
     */
    String hget(final String key,final String hkey);
    
    /**
    * @Title: hkeyExist 
    * @author lqw
    * @Description: 从redis获取hash的key是否存在 
    * @throws
     */
    boolean hkeyExist(final String key,final String hkey);
    
    /**
     * 向redis添加set类型数据
     * @param key
     * @param value
     */
    void sAdd(final String key,final String value);
    void set(final String key,final String value,final long expireTime);
    String get(final String key);
    /**
     * 从redis获取set数据
     * @param key
     * @return
     */
    Set<String> sMembers(final String key);
    
    /**
     * 判断redis是否存在key
     * @param key
     * @return
     */
    boolean keyExists(final String key);
    
    /**
     * 删除key
     * @param key
     */
    void delKey(final String key);
    
    /**
     * 选择redis数据库
     * @param dbId
     */
    void selectDb(final int dbId);
    
    
	/**
	 *得到key的个数
	 * @param key
	 * @return
	 */
    long hLen(final String key);
    
	/**
	 * 保存String
	 * @param key
	 * @param value
	 */
	void set(final String key, final String value);
	
	
	/**
	 * 设置key的过期时间
	 * @param key
	 * @param time
	 */
	void expire(final String key, final long seconds);
}
