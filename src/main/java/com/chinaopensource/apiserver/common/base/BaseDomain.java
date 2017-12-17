package com.chinaopensource.apiserver.common.base;

import java.time.LocalDateTime;

/**
 * create by lzl ON 2017/12/17
 * DB中对应实体类的父类
 */
public class BaseDomain {

    /**
     * 创建时间
     */
    protected LocalDateTime createdAt;
    /**
     * 更新时间
     */
    protected LocalDateTime updatedAt;
    /**
     *  创建人的ID
     */
    protected Long creatorId;

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }
}
