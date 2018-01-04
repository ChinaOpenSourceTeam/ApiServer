package com.chinaopensource.apiserver.blog.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.chinaopensource.apiserver.blog.data.Blog;

@Mapper
public interface BlogMapper {

	int saveBlog(Blog record);

	Blog findBlogById(Integer id);

 //   List<Blog> selectBy(String name);

    int updateBlogById(Blog record);
}
