package com.chinaopensource.apiserver.comment.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chinaopensource.apiserver.comment.data.SaveComment;
import com.chinaopensource.apiserver.comment.service.CommentService;
import com.chinaopensource.apiserver.common.constant.ResponseCode;
import com.chinaopensource.apiserver.common.controller.ControllerBase;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/comment")
public class CommentController extends ControllerBase{

	@Autowired
	private CommentService commentService;

	@ApiOperation(value="添加评论", notes="添加评论")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "Authorization", value = "token", required = true , dataType = "String" ,paramType = "header")
	})
    @PostMapping("/saveComment")
	public String saveComment(@Valid @RequestBody SaveComment saveComment){
		commentService.saveComment(saveComment);
		return renderOk(ResponseCode.OK);
	}
	
	@ApiOperation(value="查询blog的评论", notes="查询blog的评论")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "blogId", value = "blogId", dataType = "Integer" ,paramType = "query"),
		@ApiImplicitParam(name = "pageNum", value = "页数", dataType = "Integer" ,paramType = "query"),
		@ApiImplicitParam(name = "pageSize", value = "页大小", dataType = "Integer" ,paramType = "query")
	})
	@RequestMapping(value = "/findCommentByBlogId", method = RequestMethod.GET)
	public String findCommentByBlogId(@RequestParam(required=false,defaultValue="0")Integer pageNum, @RequestParam(required=false,defaultValue="10")Integer pageSize,Integer blogId){
		return renderOk(ResponseCode.OK,commentService.findCommentByBlogId(blogId, pageNum, pageSize));
	}

	
}
