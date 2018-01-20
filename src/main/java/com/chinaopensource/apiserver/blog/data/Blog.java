package com.chinaopensource.apiserver.blog.data;

import java.util.Date;

/**
 * 博客内容
 * 
 * @author lqw
 * 2018年1月4日 下午6:26:06
 */
public class Blog {
    /**
     * 主键
     */
    private Integer id;
    /**
     * 博客ID
     */
    private String uuid;
    /**
     *  标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;
    /**
     * 博客的状态
     */
    private Integer status;
    /**
     * 博客的版本
     */
    private Integer version;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 创建人ID
     */
    private Integer createUser;
    /**
     * 删除标记
     */
    private Boolean deleteFlag;
    /**
     * 更新时间
     */
    private Date updateTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
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
	@Override
	public String toString() {
		return "Blog [id=" + id + ", uuid=" + uuid + ", title=" + title + ", content=" + content + ", status=" + status
				+ ", version=" + version + ", createTime=" + createTime + ", createUser=" + createUser + ", deleteFlag="
				+ deleteFlag + ", updateTime=" + updateTime + "]";
	}
	
    
}
