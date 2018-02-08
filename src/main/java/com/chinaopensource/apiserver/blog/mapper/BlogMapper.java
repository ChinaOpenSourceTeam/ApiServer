package com.chinaopensource.apiserver.blog.mapper;

import com.chinaopensource.apiserver.blog.data.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BlogMapper {

	int saveBlog(Blog blog);

	Blog findBlogById(Integer id);

    int updateBlogById(Blog record);

	void saveBlogTags(@Param("blogId")Integer blogId, @Param("nodes")String[] nodes);

	Blog findBlogByUuidVersion(@Param("uuid") String blogUuId , @Param("version") Integer version);
	
	List<Blog> findBlogByNodeId(Integer nodeId);

	List<Blog> findListByUserIdAndDeleteFlag(@Param("user_id")Integer userId,
											 @Param("delete_flag")Boolean delete_flag,
											 @Param("type")Integer type);
}
