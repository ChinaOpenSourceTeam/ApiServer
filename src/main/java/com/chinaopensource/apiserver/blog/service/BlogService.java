package com.chinaopensource.apiserver.blog.service;

import com.chinaopensource.apiserver.blog.data.BlogPage;
import com.chinaopensource.apiserver.blog.data.SaveBlog;

public interface BlogService {

	void saveBlog(SaveBlog saveBlog);

	BlogPage findBlogByUuidVersion(String blogUuId, Integer version);

}
