package com.chinaopensource.apiserver.blog.service;

import com.chinaopensource.apiserver.blog.data.Blog;
import com.chinaopensource.apiserver.blog.data.BlogPage;
import com.chinaopensource.apiserver.blog.data.SaveBlog;

import java.util.List;

public interface BlogService {

	void saveBlog(SaveBlog saveBlog);

	BlogPage findBlogByUuidVersion(String blogUuId, Integer version);

	List<Blog> findListByUserIdAndDeleteFlag(Integer userId,Boolean deleteFlag,Integer type);

}
