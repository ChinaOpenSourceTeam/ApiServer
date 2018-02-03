package com.chinaopensource.apiserver.comment.service;

import com.chinaopensource.apiserver.comment.data.Comment;
import com.chinaopensource.apiserver.comment.data.SaveComment;
import com.github.pagehelper.PageInfo;

public interface CommentService {

	int saveComment(SaveComment saveComment);
	
	Comment findCommentById(Integer id);

	int updateByCommentId(Comment comment);
    
	PageInfo<Comment> findCommentByBlogId(Integer blogId,Integer pageNum, Integer pageSize);
}
