package com.chinaopensource.apiserver.system.user.service;

import com.chinaopensource.apiserver.common.exception.BaseException;
import com.chinaopensource.apiserver.system.user.data.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface UserService {

	/**
	 * 保存用户
	 * @param user
	 * @return
	 * @throws BaseException 
	 */
	int save(User user) throws BaseException;

	/**
	 * 更新用户信息
	 * @param user
	 * @return
	 * @throws BaseException 
	 */
	int update(User user) throws BaseException;
	/**
	 * 通过ID删除用户
	 * @param id
	 */
	void deleteUserById(Integer id);
	
	/**
	 * 通过id查找用户
	 * @param id
	 * @return
	 */
	User findUserById(Integer id);

	/**
	 * 查找所有用户
	 * @return
	 */
	List<User> findAllUser();
	
	/**
	 * 登录用户验证
	 * @param loginName
	 * @param password
	 * @return
	 */
	boolean loginValidate(String loginName,String password);

	/**
	 * 通过登录名称查找用户信息
	 * @param loginName
	 * @return
	 */
	User findUserByLoginName(String loginName);
}
