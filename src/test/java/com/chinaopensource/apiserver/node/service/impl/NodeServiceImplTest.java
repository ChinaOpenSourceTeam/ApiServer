package com.chinaopensource.apiserver.node.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.chinaopensource.apiserver.ApiServerApplicationTests;
import com.chinaopensource.apiserver.node.data.Node;
import com.chinaopensource.apiserver.node.service.NodeService;
import com.github.pagehelper.PageInfo;

public class NodeServiceImplTest extends ApiServerApplicationTests{

	@Autowired
	private NodeService ns ;
	
	@Test
	public void test() {
		PageInfo<Node> nl = ns.findNodesByNodeName("根");
		System.out.println(nl.getSize());
	}

}
