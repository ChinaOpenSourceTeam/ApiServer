package com.chinaopensource.apiserver.blog.data;

public class SaveBlog {
	
	private String title;
	private String tags;
	private String content;
	private Integer status;
	private Integer createUser;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getCreateUser() {
		return createUser;
	}
	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}
	@Override
	public String toString() {
		return "SaveBlog [title=" + title + ", tags=" + tags + ", content=" + content + ", status=" + status
				+ ", createUser=" + createUser + "]";
	}
	
	
}
