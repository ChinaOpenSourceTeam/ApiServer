package com.chinaopensource.apiserver.comment.data;

public class SaveComment {
	
	private String content;

	private Integer blogId;

	private Integer createUser;

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

	public Integer getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}

	@Override
	public String toString() {
		return "SaveComment [content=" + content + ", blogId=" + blogId + ", createUser=" + createUser + "]";
	}

}
