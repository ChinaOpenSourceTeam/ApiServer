package com.chinaopensource.apiserver.system.user.service;

import java.util.List;

import com.chinaopensource.apiserver.system.user.data.User;

public interface UserService {

	/**
	 * 保存用户
	 * @param user
	 * @return
	 */
	int save(User user);

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
}
