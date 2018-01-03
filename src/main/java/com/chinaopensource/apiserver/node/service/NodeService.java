package com.chinaopensource.apiserver.node.service;

import com.chinaopensource.apiserver.node.data.Node;
import com.github.pagehelper.PageInfo;

public interface NodeService {

    int saveNode(Node node);

    void deleteNodeById(Integer id);

    Node findNodeById(Integer id);

    int updateNodeById(Node node);

	PageInfo<Node> findNodesByNodeName(String nodeName);
	
	PageInfo<Node> findNodesByNodeName(int pageNum, int pageSize,String nodeName);
}
