package com.chinaopensource.apiserver.node.service.impl;

import com.chinaopensource.apiserver.node.data.Node;
import com.chinaopensource.apiserver.node.mapper.NodeMapper;
import com.chinaopensource.apiserver.node.service.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("nodeService")
public class NodeServiceImpl implements NodeService{

    @Autowired
    private NodeMapper nodeMapper;

    @Override
    public int saveNode(Node node) {
        return this.nodeMapper.saveNode(node);
    }

    @Override
    public void deleteNodeById(Integer id) {
        this.nodeMapper.deleteNodeById(id);
    }

    @Override
    public Node findNodeById(Integer id) {
        return this.nodeMapper.findNodeById(id);
    }

    @Override
    public int updateNodeById(Node node) {
        return this.nodeMapper.updateNodeById(node);
    }
}
