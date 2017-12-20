package com.chinaopensource.apiserver.blog.domain;

import org.springframework.stereotype.Component;

/**
 * 博客节点
 * create by lzl ON 2017/12/20
 */
@Component
public class BlogNode {

    /**
     * 博客ID
     */
    private Integer blogId;
    /**
     * 博客版本
     */
    private Integer blogVersion;
    /**
     * 节点id
     */
    private Integer nodeId;
    /**
     * 状态
     */
    private Integer status;

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

    public Integer getNodeId() {
        return nodeId;
    }

    public void setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
