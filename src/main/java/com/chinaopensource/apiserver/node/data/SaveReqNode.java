package com.chinaopensource.apiserver.node.data;

public class SaveReqNode {
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
     * 创建人
     */
    private Integer createUser;
    
    
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
	public Integer getCreateUser() {
		return createUser;
	}
	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}
	@Override
	public String toString() {
		return "SaveReqNode [name=" + name + ", description=" + description + ", pid=" + pid + ", pid1=" + pid1
				+ ", pid2=" + pid2 + ", pid3=" + pid3 + ", createUser=" + createUser + "]";
	}

}
