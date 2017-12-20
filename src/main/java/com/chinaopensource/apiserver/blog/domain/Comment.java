package com.chinaopensource.apiserver.blog.domain;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 博客的评论
 * create by lzl ON 2017/12/20
 */
@Component
public class Comment {
    /**
     * id
     */
    private Integer id;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 博客ID
     */
    private Integer blogId;
    /**
     * 博客版本
     */
    private Integer blogVersion;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 创建人id
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getBlogId() {
        return blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }

    public Integer getBlogVersion() {
        return blogVersion;
    }

    public void setBlogVersion(Integer blogVersion) {
        this.blogVersion = blogVersion;
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
