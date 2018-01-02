package com.chinaopensource.apiserver.node.service;

import com.chinaopensource.apiserver.node.data.Node;

public interface NodeService {

    int saveNode(Node node);

    void deleteNodeById(Integer id);

    Node findNodeById(Integer id);

    int updateNodeById(Node node);

}
