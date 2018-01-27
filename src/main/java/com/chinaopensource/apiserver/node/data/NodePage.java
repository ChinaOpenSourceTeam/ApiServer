package com.chinaopensource.apiserver.node.data;

import java.util.List;

import com.chinaopensource.apiserver.blog.data.Blog;
import com.chinaopensource.apiserver.system.user.data.User;

public class NodePage {

	private Node node;
	private User user;
	private List<Blog> blogList;
	
	public Node getNode() {
		return node;
	}
	public void setNode(Node node) {
		this.node = node;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<Blog> getBlogList() {
		return blogList;
	}
	public void setBlogList(List<Blog> blogList) {
		this.blogList = blogList;
	}
	
	
}
