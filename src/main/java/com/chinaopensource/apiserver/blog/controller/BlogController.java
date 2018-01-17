package com.chinaopensource.apiserver.blog.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chinaopensource.apiserver.blog.data.SaveBlog;
import com.chinaopensource.apiserver.blog.service.BlogService;
import com.chinaopensource.apiserver.common.constant.ResponseCode;
import com.chinaopensource.apiserver.common.controller.ControllerBase;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/blog")
public class BlogController extends ControllerBase{

	@Autowired
	private BlogService blogService;
	
	@ApiOperation(value="添加blog", notes="添加blog")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "Authorization", value = "token", required = true , dataType = "String" ,paramType = "header")
	})
    @PostMapping("/saveBlog")
	public String saveBlog(@Valid @RequestBody SaveBlog saveBlog){
		this.blogService.saveBlog(saveBlog);
		return renderOk(ResponseCode.OK);
	}

}
