package com.chinaopensource.apiserver.system.user.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.chinaopensource.apiserver.system.user.data.User;

@Mapper
public interface UserMapper {

	int save(User user);
}
