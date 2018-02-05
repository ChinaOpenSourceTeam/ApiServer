package com.chinaopensource.apiserver.solr.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.chinaopensource.apiserver.common.constant.ResponseCode;
import com.chinaopensource.apiserver.common.controller.ControllerBase;
import com.chinaopensource.apiserver.common.util.Base64Util;
import com.chinaopensource.apiserver.common.util.http.HttpProtocolUtil;
import com.chinaopensource.apiserver.solr.data.Docs;
import com.chinaopensource.apiserver.solr.data.SolrDomain;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/solr")
@Api(description = "solr搜索管理")
public class SolrController extends ControllerBase {

	@ApiOperation(value="使用solr,搜索blog", notes="使用solr,搜索blog")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "name", value = "节点名", dataType = "String" ,paramType = "query"),
		@ApiImplicitParam(name = "pageNum", value = "页数", dataType = "Integer" ,paramType = "query"),
		@ApiImplicitParam(name = "pageSize", value = "页大小", dataType = "Integer" ,paramType = "query")
	})
	@RequestMapping(value = "findBlogBySolr", method = RequestMethod.GET)
	public String findBlogBySolr(@RequestParam(required=false,defaultValue="0")Integer pageNum, @RequestParam(required=false,defaultValue="10")Integer pageSize,@RequestParam(required=false)String name) throws UnsupportedEncodingException{
		if(name == null || name.trim().length() == 0) {
			name ="*:*";
		}
		int start = pageNum*pageSize;
		int rows = pageSize;
		
		StringBuffer sb = new StringBuffer();
		sb.append("q=");
		sb.append(URLEncoder.encode(name.trim(), "UTF-8"));
		sb.append("&start=");
		sb.append(start);
		sb.append("&rows=");
		sb.append(rows);
		
		String result = HttpProtocolUtil.sendGet("http://www.chinaopensource.top:8983/solr/db/select", sb.toString());
		SolrDomain sd = JSON.parseObject(result, SolrDomain.class);
		List<Docs> docsList  = sd.getResponse().getDocs();
		for (Docs docs : docsList) {
			docs.setVersion(docs.getBlogVersion());
			Base64Util.EncoderContent(docs);
		}
		return renderOk(ResponseCode.OK,docsList);
	}
	
}
