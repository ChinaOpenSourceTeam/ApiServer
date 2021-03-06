package com.chinaopensource.apiserver.node.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.chinaopensource.apiserver.common.constant.ResponseCode;
import com.chinaopensource.apiserver.common.controller.ControllerBase;
import com.chinaopensource.apiserver.common.exception.BaseException;
import com.chinaopensource.apiserver.node.data.SaveReqNode;
import com.chinaopensource.apiserver.node.data.UpdateReqNode;
import com.chinaopensource.apiserver.node.service.NodeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/node")
@Api(description = "节点管理")
public class NodeController extends ControllerBase {

	@Autowired
	private NodeService nodeService;

	@ApiOperation(value="添加节点", notes="添加节点信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "Authorization", value = "token", required = true , dataType = "String" ,paramType = "header")
	})
    @PostMapping("/saveNode")
	public String saveNode(@Valid @RequestBody SaveReqNode saveNode){
		this.nodeService.saveNode(saveNode);
		return renderOk(ResponseCode.OK);
	}

	@ApiOperation(value="修改节点信息", notes="修改节点信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "Authorization", value = "token", required = true , dataType = "String" ,paramType = "header")
	})
	@RequestMapping(value = "updateNode", method = RequestMethod.PUT)
	public String updateNode(@Valid @RequestBody UpdateReqNode updatenode) throws BaseException{
		this.nodeService.updateNodeById(updatenode);
		return renderOk(ResponseCode.OK);
	}

	@ApiOperation(value="删除节点信息", notes="删除节点信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "Authorization", value = "token", required = true , dataType = "String" ,paramType = "header"),
		@ApiImplicitParam(name = "id", value = "用户Id", required = true , dataType = "Integer" ,paramType = "query")
	})
	@RequestMapping(value = "deleteNodeById", method = RequestMethod.DELETE)
	public String deleteNodeById(Integer id){
		this.nodeService.deleteNodeById(id);
		return renderOk(ResponseCode.OK);
	}
	
	@ApiOperation(value="通过ID用户信息", notes="通过ID用户信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "节点Id", required = true , dataType = "Integer" ,paramType = "query")
	})
	@RequestMapping(value = "findNodeById", method = RequestMethod.GET)
	public String findNodeById(Integer id){
		return renderOk(ResponseCode.OK,this.nodeService.findNodeById(id));
	}

	@ApiOperation(value="通过节点名查询节点列表", notes="通过节点名查询节点列表")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "name", value = "节点名", dataType = "String" ,paramType = "query"),
		@ApiImplicitParam(name = "pageNum", value = "页数", dataType = "Integer" ,paramType = "query"),
		@ApiImplicitParam(name = "pageSize", value = "页大小", dataType = "Integer" ,paramType = "query")
	})
	@RequestMapping(value = "findNodesByNodeName", method = RequestMethod.GET)
	public String findNodesByNodeName(Integer pageNum, Integer pageSize,String name){
		return renderOk(ResponseCode.OK,this.nodeService.findNodesByNodeName(pageNum,pageSize,name));
	}
	
	@ApiOperation(value="查找所有节点列表", notes="查找所有节点列表")
	@RequestMapping(value = "findAllNodes", method = RequestMethod.GET)
	public String findAllNodes(){
		return renderOk(ResponseCode.OK,this.nodeService.findAllNodes());
	}
	
	
	@RequestMapping(value = "/{nodeId}" , method = RequestMethod.GET)
	public String findNodeByNodeId(@PathVariable Integer nodeId) {
		return renderOk(ResponseCode.OK,this.nodeService.findNodeByNodeId(nodeId));
	}
	
}
