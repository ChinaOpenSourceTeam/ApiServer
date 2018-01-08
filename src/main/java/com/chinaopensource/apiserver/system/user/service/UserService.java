package com.chinaopensource.apiserver.system.user.service;

import com.chinaopensource.apiserver.common.constant.UserStatusEnum;
import com.chinaopensource.apiserver.common.exception.BaseException;
import com.chinaopensource.apiserver.system.user.data.User;

import java.util.List;

public interface UserService {

	/**
	 * 保存用户
	 * @param user
	 * @return
	 * @throws BaseException 
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
	 * 通过登录名称查找用户信息
	 * @param loginName
	 * @return
	 */
	User findUserByLoginName(String loginName);

	/**
	 * 根据登陆名判断用户是否存在
	 * @param loginName
	 * @return
	 */
	Boolean existsByLoginName(String loginName);

	/**
	 * 根据eamil判读用户是否存在
	 * @param email
	 * @return
	 */
	Boolean existsBYEmail(String email);

	/**
	 * 根据手机号码判断用户是否存在
	 * @param phone
	 * @return
	 */
	Boolean existsByPhone(String phone);

	/**
	 * 通过验证码获取user
	 * @param verificationCode
	 * @return
	 */
	User findUserByVerificationCode(String verificationCode);

	/**
	 * 根据id 激活用户状态
	 * @param id
	 * @return
	 */
	Boolean updateStatus(Integer id, UserStatusEnum userStatusEnum);

	/**
	 * 根据user的email、时间戳
	 * 生成邮箱的验证码
	 * @param email
	 * @return
	 */
	String getEmailVerificationCode(String email);

	/**
	 * 检查登录名是否已字母开头
	 * @param loginName
	 * @return
	 */
	Boolean checkLoginNameStartWith(String loginName);

	/**
	 * 检查登陆名内容
	 * @param name
	 * @return
	 */
	Boolean checkLoginNameContent(String name);

	/**
	 * 检查email是否符合规则
	 * @param email
	 * @return
	 */
	Boolean checkEmail(String email);

	/**
	 * 检查密码内容
	 * @param password
	 * @return
	 */
	Boolean checkPasswordContent(String password);

	User modifyBaseUserAttribute(User user);

	Integer updateUser(User user);

	User findByPhone(String phone);
}

