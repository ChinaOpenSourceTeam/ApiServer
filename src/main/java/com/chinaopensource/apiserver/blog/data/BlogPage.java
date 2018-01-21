package com.chinaopensource.apiserver.blog.data;

import java.util.List;

import com.chinaopensource.apiserver.node.data.Node;
import com.chinaopensource.apiserver.system.user.data.User;

public class BlogPage {

	private User user;
	
	private Blog blog;

	private List<Node> nodes;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Blog getBlog() {
		return blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}

	public List<Node> getNodes() {
		return nodes;
	}

	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}

	@Override
	public String toString() {
		return "BlogPage [user=" + user + ", blog=" + blog + ", nodes=" + nodes + "]";
	}


}
