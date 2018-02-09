package com.chinaopensource.apiserver.system.user.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.chinaopensource.apiserver.ApiServerApplicationTests;
import com.chinaopensource.apiserver.node.data.Node;
import com.chinaopensource.apiserver.node.service.NodeService;
import com.chinaopensource.apiserver.system.user.data.User;
import com.chinaopensource.apiserver.system.user.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.util.List;

import static org.junit.Assert.fail;

public class UserControllerTest  extends ApiServerApplicationTests{

	@Autowired
	private MockHttpServletRequest request;
	
	@Autowired
	private MockHttpServletResponse response;

	@Autowired
	private UserService userService;

	@Autowired
	private NodeService nodeService;

	@Test
	public void testSaveUser() {

	}

	@Test
	public void testUpdateUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteUserById() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindUserById() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindUserByLoginName() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindAllUser() {
		this.request.setRequestURI("/");
		this.request.setMethod("GET");
	
	}

	@Test
	public void getBlogType(){
		Integer id =12;
		List<Node> nodeList = nodeService.findListByUserIdAndDelete(id,false);
		System.out.println(JSONUtils.toJSONString("niasd"));
	}

	@Test
	public void json(){
		int id = 12;
		User user = userService.findUserById(id);
		System.out.println(JSONUtils.toJSONString(user));
	}

}
