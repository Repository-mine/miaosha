package com.miaosha.controller;

import com.miaosha.controller.vo.UserVO;
import com.miaosha.error.BusinessException;
import com.miaosha.error.EmBusinessError;
import com.miaosha.response.CommonReturnType;
import com.miaosha.service.UserService;
import com.miaosha.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

	@Autowired
	private UserService userService;

	private UserVO convertFromModel(UserModel userModel) {
		UserVO userVO = new UserVO();
		BeanUtils.copyProperties(userModel, userVO);
		return userVO;
	}

	@RequestMapping("/get")
	@ResponseBody
	public CommonReturnType get(@RequestParam(name = "id", defaultValue = "1") int id) throws BusinessException {
		UserModel userModel = userService.getUserById(id);

		if (userModel == null) {
			throw new BusinessException(EmBusinessError.USER_NOT_EXIST);
		}
		UserVO userVO = convertFromModel(userModel);

		return CommonReturnType.creat(userVO);

	}

}
