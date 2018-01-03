package com.chinaopensource.apiserver.node.service.impl;

import com.chinaopensource.apiserver.node.data.Node;
import com.chinaopensource.apiserver.node.mapper.NodeMapper;
import com.chinaopensource.apiserver.node.service.NodeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

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

	@Override
	public PageInfo<Node> findNodesByNodeName(int pageNum, int pageSize,String nodeName) {
		//获取第1页，10条内容，默认查询总数count
	    PageHelper.startPage(pageNum, pageSize);
	    //紧跟着的第一个select方法会被分页
	    List<Node> list =  this.nodeMapper.findNodesByNodeName(nodeName);
	    
	    PageInfo<Node> info = new PageInfo<Node>(list);
	    
		return info;
	}

	@Override
	public PageInfo<Node> findNodesByNodeName(String nodeName) {
		return this.findNodesByNodeName(0, 10, nodeName);
	}
}
