package com.chinaopensource.apiserver.common.constant;

/**
 * 用户状态的枚举
 * create by lzl ON 2017/12/20
 */
public enum UserStatusEnum {
    UN_ACTIVATE(0,"未激活"),
    ACTIVATED(1,"激活"),
    MUTE(2,"禁言"),
    FORBIDDEN(3,"禁用/封号"),
    ;
    UserStatusEnum(Integer status,String description){
        this.status = status;
        this.description = description;
    }

    /**
     * 状态
     */
    private Integer status;
    /**
     * 状态描述
     */
    private String description;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
