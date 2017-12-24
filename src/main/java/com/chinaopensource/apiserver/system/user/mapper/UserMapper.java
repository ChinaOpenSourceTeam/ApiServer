package com.chinaopensource.apiserver.system.user.mapper;

import java.util.List;

import com.chinaopensource.apiserver.system.user.data.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

	int save(User user);

	void delete(Integer id);

	User findUserById(Integer id);

	List<User> findAllUser();

	void update(User user);

	String findPasswordByLoginName(String loginName);

	User findUserByLoginName(String loginName);

	User findByPhone(String phone);

	User findByEmail(String email);

	/**
	 * 通过验证码获取用户
	 * @param verificationCode
	 * @return
	 */
	User findUserByVerificationCode(String verificationCode);

	/**
	 * 根据id 更新用户状态
	 * @param id
	 * @param status
	 * @return
	 */
	Boolean updateStatus(Long id,Integer status);

}
