package com.chinaopensource.apiserver.system.login.data;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

@Component
public class ValData {
	
	@NotEmpty()
	@Length(min=6,max=10)
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
