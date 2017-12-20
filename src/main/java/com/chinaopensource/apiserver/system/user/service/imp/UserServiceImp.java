package com.chinaopensource.apiserver.system.user.service.imp;

import com.chinaopensource.apiserver.common.exception.BaseException;
import com.chinaopensource.apiserver.common.exception.HasException;
import com.chinaopensource.apiserver.common.exception.NoHasException;
import com.chinaopensource.apiserver.common.util.encryption.EncryptionUtil;
import com.chinaopensource.apiserver.system.user.data.BaseUser;
import com.chinaopensource.apiserver.system.user.data.User;
import com.chinaopensource.apiserver.system.user.mapper.UserMapper;
import com.chinaopensource.apiserver.system.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public int save(User user) throws BaseException {
		this.existValidate(user.getLoginName(),false);
		//进行加密
		user.setPassword(EncryptionUtil.getHash(user.getPassword(), "MD5"));
//		userMapper.save(user);
		return 0;
	}

	@Override
	public int update(BaseUser user) throws BaseException {
		this.existValidate(user.getLoginName(), false);
		userMapper.update(user);
		return 0;
	}
	
	@Override
	public void deleteUserById(Integer id) {
		userMapper.delete(id);
	}

	@Override
	public BaseUser findUserById(Integer id) {
		return userMapper.findUserById(id);
	}

	@Override
	public List<BaseUser> findAllUser() {
		return userMapper.findAllUser();
	}

	@Override
	public boolean loginValidate(String loginName, String password) {
		String pwd = userMapper.findPasswordByLoginName(loginName);
		return EncryptionUtil.getHash(password, "MD5").equals(pwd);
	}

	@Override
	public BaseUser findUserByLoginName(String loginName) {
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
		BaseUser u= this.findUserByLoginName(loginName);
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
}
