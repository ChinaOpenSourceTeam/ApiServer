package com.chinaopensource.apiserver.comment.data;

import java.util.Date;

public class Comment {
	
    private Integer id;

    private String content;

    private Integer blogId;

    private Date createTime;

    private Integer createUser;
    /**
     * 创建人名字
     */
    private String createUserName;

    private Boolean deleteFlag;

    private Date updateTime;

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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}

	public Boolean getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", content=" + content + ", blogId=" + blogId + ", createTime=" + createTime
				+ ", createUser=" + createUser + ", createUserName=" + createUserName + ", deleteFlag=" + deleteFlag
				+ ", updateTime=" + updateTime + "]";
	}

}