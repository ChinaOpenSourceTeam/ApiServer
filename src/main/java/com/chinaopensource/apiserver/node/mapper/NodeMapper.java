package com.chinaopensource.apiserver.node.mapper;

import com.chinaopensource.apiserver.node.data.Node;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface NodeMapper {

    int saveNode(Node node);

    Node findNodeById(Integer id);

    int updateNodeById(Node node);

	List<Node> findNodesByNodeName(@Param("nodeName") String nodeName);
	
	List<Node> findNodesByBlogId(@Param("blogId") Integer blogId);

    List<Node> findListByUserIdAndDelete(@Param("userId")Integer userId,
                                         @Param("delete")Boolean delete);
}