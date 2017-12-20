package com.chinaopensource.apiserver.blog.domain;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 节点
 * create by lzl ON 2017/12/20
 */
@Component
public class Node {

    /**
     * id
     */
    private Integer id;
    /**
     * 节点名称
     */
    private String name;
    /**
     * 节点描述
     */
    private String description;
    /**
     * 父主节点
     */
    private Integer pid;
    /**
     * 父主节点1
     */
    private Integer pid1;
    /**
     * 父主节点2
     */
    private Integer pid2;
    /**
     * 父主节点3
     */
    private Integer pid3;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 创建人
     */
    private Integer createUser;
    /**
     * 是否删除 0 未删除，1已删除
     */
    private Integer deleteFlag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getPid1() {
        return pid1;
    }

    public void setPid1(Integer pid1) {
        this.pid1 = pid1;
    }

    public Integer getPid2() {
        return pid2;
    }

    public void setPid2(Integer pid2) {
        this.pid2 = pid2;
    }

    public Integer getPid3() {
        return pid3;
    }

    public void setPid3(Integer pid3) {
        this.pid3 = pid3;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}

