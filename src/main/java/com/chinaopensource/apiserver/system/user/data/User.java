package com.chinaopensource.apiserver.system.user.data;

import com.chinaopensource.apiserver.common.constant.UserStatusEnum;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 *  用户表
 */
@Component
public class User implements Serializable{
    /**
     * 主键id
     */
    private Integer id;
    /**
     * 账号 （个性化账号）
     */
    private String loginName;
    /**
     * 密码
     */
	private String password;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 年龄
     */
    private  Integer age;
    /**
     * 手机号码（账号之一）
     */
    private String phone;
    /**
     * 头像的URL
     */
    private String photo;
    /**
     * 地址
     */
    private String address;
    /**
     * 邮箱 （账号之一）
     */
    private String email;
    /**
     * 用户的状态 未激活0、激活1、禁言2、禁用3
     */
    private Integer status;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 是否删除 0未删除、1已删除
     */
    private Integer deleteFlag;
    /**
     * 邮箱验证码
     */
    private String verificationCode;

    public User(){

    }

    public User(String loginName, String password, String nickName, Integer age, String phone, String photo, String address,
                String email, UserStatusEnum statusEnum, Date createTime, Integer deleteFlag, String verificationCode) {
        this.loginName = loginName;
        this.password = password;
        this.nickName = nickName;
        this.age = age;
        this.phone = phone;
        this.photo = photo;
        this.address = address;
        this.email = email;
        this.status = statusEnum.getStatus();
        this.createTime = createTime;
        this.deleteFlag = deleteFlag;
        this.verificationCode = verificationCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
