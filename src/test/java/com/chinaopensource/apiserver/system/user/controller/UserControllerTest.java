package com.chinaopensource.apiserver.system.user.controller;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.bind.annotation.RequestMethod;

import com.chinaopensource.apiserver.ApiServerApplicationTests;

public class UserControllerTest  extends ApiServerApplicationTests{

	@Autowired
	private MockHttpServletRequest request;
	
	@Autowired
	private MockHttpServletResponse response;
	
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

}
