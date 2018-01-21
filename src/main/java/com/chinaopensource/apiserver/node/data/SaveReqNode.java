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
    private String pids;
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
	public String getPids() {
		return pids;
	}
	public void setPids(String pids) {
		this.pids = pids;
	}
	public Integer getCreateUser() {
		return createUser;
	}
	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}
	@Override
	public String toString() {
		return "SaveReqNode [name=" + name + ", description=" + description + ", pids=" + pids + ", createUser="
				+ createUser + "]";
	}
	
}
