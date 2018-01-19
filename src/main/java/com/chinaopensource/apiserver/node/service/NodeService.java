package com.chinaopensource.apiserver.node.service;

import java.util.List;

import com.chinaopensource.apiserver.node.data.Node;
import com.chinaopensource.apiserver.node.data.SaveReqNode;
import com.chinaopensource.apiserver.node.data.UpdateReqNode;
import com.github.pagehelper.PageInfo;

public interface NodeService {

    int saveNode(SaveReqNode saveNode);

    void deleteNodeById(Integer id);

    Node findNodeById(Integer id);

    int updateNodeById(UpdateReqNode updatenode);

	PageInfo<Node> findNodesByNodeName(String nodeName);
	
	PageInfo<Node> findNodesByNodeName(int pageNum, int pageSize,String nodeName);

	List<Node> findAllNodes();
}
