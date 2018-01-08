package com.chinaopensource.apiserver.system.user.service.imp;

import com.chinaopensource.apiserver.common.constant.EncryptionEnum;
import com.chinaopensource.apiserver.common.constant.UserStatusEnum;
import com.chinaopensource.apiserver.common.util.encryption.EncryptionUtil;
import com.chinaopensource.apiserver.system.user.data.User;
import com.chinaopensource.apiserver.system.user.mapper.UserMapper;
import com.chinaopensource.apiserver.system.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

@Service
public class UserServiceImp implements UserService {

	private static final Pattern pattern =
			Pattern.compile("^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9\\u4e00-\\u9fa5]+(\\.[a-zA-Z0-9\\u4e00-\\u9fa5]+)+$");

	@Autowired
	private UserMapper userMapper;
	
	@Override
	@Transactional
	public int save(User user) {
		return userMapper.save(user);
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
	public User findUserByLoginName(String loginName) {
		return userMapper.findUserByLoginName(loginName);
	}

	@Override
	public Boolean existsByLoginName(String loginName) {
		return Objects.isNull(userMapper.findUserByLoginName(loginName));
	}

	@Override
	public Boolean existsBYEmail(String email) {
		return Objects.isNull(userMapper.findByEmail(email));
	}

	@Override
	public Boolean existsByPhone(String phone) {
		return null;
	}

	@Override
	public User findUserByVerificationCode(String verificationCode) {
		return userMapper.findByVerificationCode(verificationCode);
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

	/**
	 * 根据user的loginName、时间戳
	 * 生成邮箱的验证码
	 * @param email
	 * @return
	 */
	@Override
	public String getEmailVerificationCode(String email) {
		StringBuilder sb  = new StringBuilder();
		sb.append(email);
		sb.append(":");
		sb.append(LocalDateTime.now().toLocalDate());
		return EncryptionUtil.getHash(sb.toString(), EncryptionEnum.MD5);
	}

	/**
	 * 检查登录名是否已字母开头
	 * @param loginName
	 * @return
	 */
	@Override
	public Boolean checkLoginNameStartWith(String loginName) {
		char start = loginName.charAt(0);
		if((start >= 'a' && start <= 'z' )|| (start >= 'A' && start <= 'Z')){
			return true;
		}else {
			return false;
		}
	}

	/**
	 * 检查登录名内容（符合内容）
	 * 字母、数字、下划线
	 * @param name
	 * @return
	 */
	@Override
	public Boolean checkLoginNameContent(String name) {
		for(char content: name.toCharArray()){
			if(!isLegal(content)){
				return false;
			}
		}
		return true;
	}

	/**
	 * 判断字符是否是字母
	 * @param ch
	 * @return
	 */
	private Boolean isGrapheme(char ch){
		if((ch >= 'a' && ch <= 'z' )|| ( ch >= 'A' && ch <= 'Z')){
			return true;
		}else {
			return false;
		}
	}

	/**
	 * 检查字符是否是字母或者数字
	 * @param ch
	 * @return
	 */
	private Boolean isGraphemeOrNumber(char ch){
		return isGrapheme(ch) || (ch >= '0' && ch <= '9');
	}

	/**
	 * 判断字符是否是合法字符
	 * 字母、数字、下划线
	 * @param ch
	 * @return
	 */
	private Boolean isLegal(char ch){
		return isGraphemeOrNumber(ch) || ('_' == ch);
	}

	/**
	 * 检查邮箱 是否符合规则
	 * @param email
	 * @return
	 */
	@Override
	public Boolean checkEmail(String email) {
		return pattern.matcher(email).matches();
	}

	/**
	 * 检查密码内容
	 * @param password
	 * @return
	 */
	@Override
	public Boolean checkPasswordContent(String password) {
		for(char ch : password.toCharArray()){
			if(!isGraphemeOrNumber(ch)){
				return false;
			}
		}
		return true;
	}

	/**
	 * 传入的user，根据需要展示user的某些属性
	 * @param user
	 * @return
	 */
	@Override
	public User modifyBaseUserAttribute(User user) {
		User baseUser = new User();
		baseUser.setLoginName(user.getLoginName());
		baseUser.setPhone(user.getPhone());
		baseUser.setEmail(user.getEmail());
		baseUser.setAddress(user.getAddress());
		return baseUser;
	}

	@Override
	public Integer updateUser(User user) {
		return userMapper.updateUser(user);
	}

	@Override
	public User findByPhone(String phone) {
		return userMapper.findByPhone(phone);
	}
}
