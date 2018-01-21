package com.chinaopensource.apiserver.blog.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.chinaopensource.apiserver.blog.data.Blog;

@Mapper
public interface BlogMapper {

	int saveBlog(Blog blog);

	Blog findBlogById(Integer id);

 //   List<Blog> selectBy(String name);

    int updateBlogById(Blog record);

	void saveBlogTags(@Param("blogId")Integer blogId, @Param("nodes")String[] nodes);

	Blog findBlogByUuidVersion(@Param("uuid") String blogUuId , @Param("version") Integer version);
}
