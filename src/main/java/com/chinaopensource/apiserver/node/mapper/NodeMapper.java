package com.chinaopensource.apiserver.node.mapper;

import com.chinaopensource.apiserver.node.data.Node;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface NodeMapper {

    int saveNode(Node node);

    void deleteNodeById(Integer id);

    Node findNodeById(Integer id);

    int updateNodeById(Node node);

}