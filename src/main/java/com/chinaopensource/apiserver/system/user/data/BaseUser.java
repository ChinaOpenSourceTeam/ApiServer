package com.chinaopensource.apiserver.system.user.data;

import javax.validation.constraints.NotNull;

import com.chinaopensource.apiserver.common.base.BaseDomain;
import org.hibernate.validator.constraints.Length;

public class BaseUser extends BaseDomain {
	
	int id;
	@NotNull
	@Length(min=6,max=20)
	String loginName;
	@NotNull
	String userName;
	int age; 
	String phone;
	String photo;
	String address;
	String email;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
