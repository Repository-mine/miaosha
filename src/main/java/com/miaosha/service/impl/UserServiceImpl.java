package com.miaosha.service.impl;

import com.miaosha.mapper.UserDOMapper;
import com.miaosha.mapper.UserPasswordDOMapper;
import com.miaosha.pojo.UserDO;
import com.miaosha.pojo.UserPasswordDO;
import com.miaosha.service.UserService;
import com.miaosha.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDOMapper userDOMapper;

	@Autowired
	private UserPasswordDOMapper userPasswordDOMapper;

	@Override
	public UserModel getUserById(Integer id) {
		UserDO user = userDOMapper.selectByPrimaryKey(id);
		if (user == null) {
			return null;
		}
		UserPasswordDO userPasswordDO = userPasswordDOMapper.getUserPwdById(user.getId());
		return convertFromDataObject(user, userPasswordDO);
	}

	private UserModel convertFromDataObject(UserDO userDO, UserPasswordDO userPasswordDO) {
		UserModel userModel = new UserModel();
		BeanUtils.copyProperties(userDO, userModel);
		if (userPasswordDO != null) {
			BeanUtils.copyProperties(userPasswordDO, userModel);
		}
		return userModel;
	}
}
