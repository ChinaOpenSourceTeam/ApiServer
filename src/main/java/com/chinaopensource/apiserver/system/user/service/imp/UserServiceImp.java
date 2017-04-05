package com.chinaopensource.apiserver.system.user.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chinaopensource.apiserver.common.util.encryption.EncryptionUtil;
import com.chinaopensource.apiserver.system.user.data.User;
import com.chinaopensource.apiserver.system.user.mapper.UserMapper;
import com.chinaopensource.apiserver.system.user.service.UserService;

@Component("userService")
public class UserServiceImp implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public int save(User user) {
		if(user.getId()==0){
			//登录名是否存在
			User u= userMapper.findUserByLoginName(user.getLoginName());
			if (u!=null){
				// TODO service 异常返回错误 解决办法    用户已经存在
				return 0;
			}
			//进行加密
			user.setPassword(EncryptionUtil.getHash(user.getPassword(), "MD5"));
			userMapper.save(user);
		}
		else
			userMapper.update(user);
		return 0;
	}

	@Override
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
		return EncryptionUtil.getHash(password, "MD5").equals(pwd);
	}

	@Override
	public User findUserByLoginName(String loginName) {
		return userMapper.findUserByLoginName(loginName);
	}

}
