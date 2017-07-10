package com.chinaopensource.apiserver.system.user.data;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

@Component
public class User extends BaseUser{

	@NotNull
	@Length(min=6)
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
