package com.chinaopensource.apiserver.node.service.impl;

import com.chinaopensource.apiserver.node.data.Node;
import com.chinaopensource.apiserver.node.data.SaveReqNode;
import com.chinaopensource.apiserver.node.data.UpdateReqNode;
import com.chinaopensource.apiserver.node.mapper.NodeMapper;
import com.chinaopensource.apiserver.node.service.NodeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("nodeService")
public class NodeServiceImpl implements NodeService{

    @Autowired
    private NodeMapper nodeMapper;

    @Override
    public int saveNode(SaveReqNode saveNode) {
    	Node node =  new Node();
    	node.setName(saveNode.getName());
    	node.setPid(saveNode.getPid());
    	node.setPid1(saveNode.getPid1());
    	node.setPid2(saveNode.getPid2());
    	node.setPid3(saveNode.getPid3());
    	node.setDescription(saveNode.getDescription());
    	node.setCreateUser(saveNode.getCreateUser());
    	
    	node.setCreateTime(new Date());
    	node.setDeleteFlag(false);
    	node.setStatus(0);
    	
        return this.nodeMapper.saveNode(node);
    }

    @Override
    public void deleteNodeById(Integer id) {
    	Node node =  new Node();
    	node.setId(id);
    	node.setDeleteFlag(true);
        this.nodeMapper.updateNodeById(node);
    }

    @Override
    public Node findNodeById(Integer id) {
        return this.nodeMapper.findNodeById(id);
    }

    @Override
    public int updateNodeById(UpdateReqNode updatenode) {
    	Node node =  new Node();
    	node.setId(updatenode.getId());
    	node.setName(updatenode.getName());
    	node.setPid(updatenode.getPid());
    	node.setPid1(updatenode.getPid1());
    	node.setPid2(updatenode.getPid2());
    	node.setPid3(updatenode.getPid3());
    	node.setDescription(updatenode.getDescription());
    	
//    	node.setStatus(0);
    	node.setUpdateTime(new Date());
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
