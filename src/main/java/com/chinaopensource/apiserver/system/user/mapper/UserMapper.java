package com.chinaopensource.apiserver.system.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.chinaopensource.apiserver.system.user.data.BaseUser;

@Mapper
public interface UserMapper {

	int save(BaseUser user);

	void delete(Integer id);

	BaseUser findUserById(Integer id);

	List<BaseUser> findAllUser();

	void update(BaseUser user);

	String findPasswordByLoginName(String loginName);

	BaseUser findUserByLoginName(String loginName);
}
