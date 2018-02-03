package com.chinaopensource.apiserver.comment.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chinaopensource.apiserver.comment.data.Comment;
import com.chinaopensource.apiserver.comment.data.SaveComment;
import com.chinaopensource.apiserver.comment.mapper.CommentMapper;
import com.chinaopensource.apiserver.comment.service.CommentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service("commentService")
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentMapper commentMapper;
	
	@Override
	public int saveComment(SaveComment saveComment) {
		Comment comment = new Comment();
		comment.setContent(saveComment.getContent());
		comment.setBlogId(saveComment.getBlogId());
		comment.setCreateUser(saveComment.getCreateUser());
		comment.setCreateTime(new Date());
		comment.setUpdateTime(new Date());
		comment.setDeleteFlag(false);
		return commentMapper.saveComment(comment);
	}

	@Override
	public Comment findCommentById(Integer id) {
		return commentMapper.findCommentById(id);
	}

	@Override
	public int updateByCommentId(Comment comment) {
		return commentMapper.updateByCommentId(comment);
	}

	@Override
	public PageInfo<Comment> findCommentByBlogId(Integer blogId, Integer pageNum, Integer pageSize) {
		//获取第1页，10条内容，默认查询总数count
	    PageHelper.startPage(pageNum, pageSize);
	    List<Comment> commentList= commentMapper.findCommentByBlogId(blogId);
		return new PageInfo<Comment>(commentList);
	}

}
