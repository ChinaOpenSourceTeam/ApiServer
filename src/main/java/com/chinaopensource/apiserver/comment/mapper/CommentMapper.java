package com.chinaopensource.apiserver.comment.mapper;

import com.chinaopensource.apiserver.comment.data.Comment;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper {

	int saveComment(Comment comment);
	
	Comment findCommentById(Integer id);

	int updateByCommentId(Comment comment);
    
	List<Comment> findCommentByBlogId(Integer blogId);
}