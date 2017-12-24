package com.chinaopensource.apiserver.system.user.service;

import com.chinaopensource.apiserver.common.constant.UserStatusEnum;
import com.chinaopensource.apiserver.common.exception.BaseException;
import com.chinaopensource.apiserver.system.user.data.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
	Boolean updateStatus(Long id, UserStatusEnum userStatusEnum);
}
