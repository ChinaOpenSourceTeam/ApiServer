package com.chinaopensource.apiserver.system.login.data;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * 登录参数校验
 * 
 * @author lqw
 * 2017年4月8日 下午8:27:09
 */
@ApiModel
@Component
public class LoginData {
	
	@ApiModelProperty(value="用户名",example="liqiwei")
	@NotEmpty()
	@Length(min=6,max=20)
	private String loginName;
	
	@ApiModelProperty(value="密码",example="123456")
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
