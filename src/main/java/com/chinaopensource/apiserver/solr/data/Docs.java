package com.chinaopensource.apiserver.solr.data;

public class Docs {
	
    private String id;
    private String uuid;
	private String title;
    private String content;
    private int createUser;
    private String createUserName;
    private String createTime;
    private int blogVersion;
    private long version;
    private int status;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getCreateUser() {
		return createUser;
	}
	public void setCreateUser(int createUser) {
		this.createUser = createUser;
	}
	public String getCreateUserName() {
		return createUserName;
	}
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public int getBlogVersion() {
		return blogVersion;
	}
	public void setBlogVersion(int blogVersion) {
		this.blogVersion = blogVersion;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public long getVersion() {
		return version;
	}
	public void setVersion(long version) {
		this.version = version;
	}
	@Override
	public String toString() {
		return "Docs [id=" + id + ", uuid=" + uuid + ", title=" + title + ", content=" + content + ", createUser="
				+ createUser + ", createUserName=" + createUserName + ", createTime=" + createTime + ", blogVersion="
				+ blogVersion + ", version=" + version + ", status=" + status + "]";
	}
	
}
