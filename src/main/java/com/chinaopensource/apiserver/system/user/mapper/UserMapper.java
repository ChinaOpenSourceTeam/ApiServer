package com.chinaopensource.apiserver.system.user.mapper;

import java.util.List;

import com.chinaopensource.apiserver.system.user.data.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

	int save(@Param("User") User user);

	void delete(@Param("id") Integer id);

	User findUserById(@Param("id") Integer id);

	List<User> findAllUser();

	String findPasswordByLoginName(@Param("loginName") String loginName);

	User findUserByLoginName(@Param("loginName") String loginName);

	User findByPhone(@Param("phone") String phone);

	User findByEmail(@Param("email") String email);

	/**
	 * 通过验证码获取用户
	 * @param verificationCode
	 * @return
	 */
	User findByVerificationCode(@Param("verificationCode") String verificationCode);

	/**
	 * 根据id 更新用户状态
	 * @param id
	 * @param status
	 * @return
	 */
	Boolean updateStatus(@Param("id") Integer id,@Param("status") Integer status);

	Integer updateUser(@Param("user")User user);

}
