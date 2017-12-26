package com.chinaopensource.apiserver.system.user.service.imp;

import com.chinaopensource.apiserver.common.constant.EncryptionEnum;
import com.chinaopensource.apiserver.common.constant.UserStatusEnum;
import com.chinaopensource.apiserver.common.exception.BaseException;
import com.chinaopensource.apiserver.common.exception.HasException;
import com.chinaopensource.apiserver.common.exception.NoHasException;
import com.chinaopensource.apiserver.common.util.encryption.EncryptionUtil;
import com.chinaopensource.apiserver.system.user.data.User;
import com.chinaopensource.apiserver.system.user.mapper.UserMapper;
import com.chinaopensource.apiserver.system.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Override
	@Transactional
	public int save(User user) throws BaseException {
		this.existValidate(user.getLoginName(),false);
		//进行加密
		user.setPassword(EncryptionUtil.getHash(user.getPassword(), EncryptionEnum.MD5));
//		userMapper.save(user);
		return 0;
	}

	@Override
	@Transactional
	public int update(User user) throws BaseException {
		this.existValidate(user.getLoginName(), false);
		userMapper.update(user);
		return 0;
	}
	
	@Override
	@Transactional
	public void deleteUserById(Integer id) {
		userMapper.delete(id);
	}

	@Override
	public User findUserById(Integer id) {
		return userMapper.findUserById(id);
	}

	@Override
	public List<User> findAllUser() {
		return userMapper.findAllUser();
	}

	@Override
	public boolean loginValidate(String loginName, String password) {
		String pwd = userMapper.findPasswordByLoginName(loginName);
		return EncryptionUtil.getHash(password, EncryptionEnum.MD5).equals(pwd);
	}

	@Override
	public User findUserByLoginName(String loginName) {
		return userMapper.findUserByLoginName(loginName);
	}

	/*
	 * 登录名  是否存在
	 * 
	 * loginName   登录名
	 * flag        是否存在     true 表示登录名存在,如果不存在抛异常     
	 *                   false 表示登录名不存在,如果存在就抛异常
	 */
	private void existValidate(String loginName,boolean flag) throws BaseException{
		//登录名是否存在
		User u= this.findUserByLoginName(loginName);
		if (flag){
			if(u==null){
				throw new NoHasException(loginName);
			}
		} else {
			if(u!=null){
				throw new HasException(loginName);
			}
		}
	}

	@Override
	public Boolean existsByLoginName(String loginName) {
		return Objects.isNull(userMapper.findUserByLoginName(loginName));
	}

	@Override
	public Boolean existsBYEmail(String email) {
		return null;
	}

	@Override
	public Boolean existsByPhone(String phone) {
		return null;
	}

	@Override
	public User findUserByVerificationCode(String verificationCode) {
		return userMapper.findUserByVerificationCode(verificationCode);
	}

	/**
	 * 根据ID 设置用户的激活状态
	 * @param id
	 * @param userStatusEnum
	 * @return
	 */
	@Override
	@Transactional
	public Boolean updateStatus(Integer id, UserStatusEnum userStatusEnum) {
		return userMapper.updateStatus(id,userStatusEnum.getStatus());
	}
}
