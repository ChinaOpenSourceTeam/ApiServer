package com.chinaopensource.apiserver.blog.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chinaopensource.apiserver.blog.data.Blog;
import com.chinaopensource.apiserver.blog.data.BlogPage;
import com.chinaopensource.apiserver.blog.data.SaveBlog;
import com.chinaopensource.apiserver.blog.mapper.BlogMapper;
import com.chinaopensource.apiserver.blog.service.BlogService;
import com.chinaopensource.apiserver.common.util.Base64Util;
import com.chinaopensource.apiserver.node.data.Node;
import com.chinaopensource.apiserver.node.mapper.NodeMapper;
import com.chinaopensource.apiserver.system.user.data.User;
import com.chinaopensource.apiserver.system.user.mapper.UserMapper;

@Service("blogService")
public class BlogServiceImpl implements BlogService {
	
	@Autowired
	private BlogMapper blogMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private NodeMapper nodeMapper;

	@Override
	public void saveBlog(SaveBlog saveBlog) {
		String blogId = UUID.randomUUID().toString();
		Blog blog = new Blog();
		blog.setUuid(blogId);
		blog.setTitle(saveBlog.getTitle());
		blog.setContent(saveBlog.getContent());
		blog.setStatus(saveBlog.getStatus());
		blog.setVersion(1);
		blog.setCreateUser(saveBlog.getCreateUser());
		blog.setCreateTime(new Date());
		blog.setUpdateTime(new Date());
		blog.setDeleteFlag(false);
		Base64Util.DecoderContent(blog);
		this.blogMapper.saveBlog(blog);
		
		String tags = saveBlog.getTags();
		if(tags== null || tags.trim().isEmpty() || tags.trim().equals(",")) {
			return;
		}
		String[] nodes = tags.split(",");
		this.blogMapper.saveBlogTags(blog.getId(),nodes);
	}

	@Override
	public BlogPage findBlogByUuidVersion(String blogUuId, Integer version) {
		BlogPage blogPage = new BlogPage();
		Blog blog = this.blogMapper.findBlogByUuidVersion(blogUuId,version);
		blogPage.setBlog(blog);
		User user = this.userMapper.findUserById(blog.getCreateUser());
		blogPage.setUser(user);
		List<Node> nodeList = this.nodeMapper.findNodesByBlogId(blog.getId());
		blogPage.setNodes(nodeList);
		return blogPage;
	}
	
}
