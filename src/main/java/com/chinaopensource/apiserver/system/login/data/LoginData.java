package com.chinaopensource.apiserver.system.login.data;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;
/**
 * 登录参数校验
 * 
 * @author lqw
 * 2017年4月8日 下午8:27:09
 */
@Component
public class LoginData {
	
	@NotEmpty()
	@Length(min=6,max=20)
	private String loginName;
	@Length(min=6)
	private String password;
	
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "ValData [loginName=" + loginName + ", password=" + password + "]";
	}
	
}
