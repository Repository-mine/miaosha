package com.miaosha.mapper;

import com.miaosha.pojo.UserPasswordDO;

public interface UserPasswordDOMapper {
	UserPasswordDO getUserPwdById(Integer id);

	int deleteByPrimaryKey(Integer id);

	int insert(UserPasswordDO record);

	int insertSelective(UserPasswordDO record);

	UserPasswordDO selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(UserPasswordDO record);

	int updateByPrimaryKey(UserPasswordDO record);
}