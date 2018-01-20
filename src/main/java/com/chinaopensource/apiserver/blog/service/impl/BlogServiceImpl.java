package com.chinaopensource.apiserver.blog.service.impl;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chinaopensource.apiserver.blog.data.Blog;
import com.chinaopensource.apiserver.blog.data.SaveBlog;
import com.chinaopensource.apiserver.blog.mapper.BlogMapper;
import com.chinaopensource.apiserver.blog.service.BlogService;

@Service("blogService")
public class BlogServiceImpl implements BlogService {
	
	@Autowired
	private BlogMapper blogMapper;

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
		
		this.blogMapper.saveBlog(blog);
		
		String tags = saveBlog.getTags();
		if(tags== null || tags.trim().isEmpty() || tags.trim().equals(",")) {
			return;
		}
		String[] nodes = tags.split(",");
		this.blogMapper.saveBlogTags(blog.getId(),nodes);
	}
	
}
