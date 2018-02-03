package com.chinaopensource.apiserver.blog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.chinaopensource.apiserver.blog.data.Blog;

@Mapper
public interface BlogMapper {

	int saveBlog(Blog blog);

	Blog findBlogById(Integer id);

    int updateBlogById(Blog record);

	void saveBlogTags(@Param("blogId")Integer blogId, @Param("nodes")String[] nodes);

	Blog findBlogByUuidVersion(@Param("uuid") String blogUuId , @Param("version") Integer version);
	
	List<Blog> findBlogByNodeId(Integer nodeId);
}
