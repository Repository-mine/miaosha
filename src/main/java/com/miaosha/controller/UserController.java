package com.miaosha.controller;

import com.alibaba.druid.util.StringUtils;
import com.miaosha.controller.vo.UserVO;
import com.miaosha.error.BusinessException;
import com.miaosha.error.EmBusinessError;
import com.miaosha.response.CommonReturnType;
import com.miaosha.service.UserService;
import com.miaosha.service.model.UserModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;

@Slf4j
@Controller
@RequestMapping("/user")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class UserController {
    public static final String CONTENT_TYPE_FORMED = "application/x-www-form-urlencoded";
    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @RequestMapping("/login")
    @ResponseBody
    public CommonReturnType login(@RequestParam(name = "telphone") String telphone,
                                  @RequestParam(name = "password") String password) throws BusinessException {
        if (StringUtils.isEmpty(telphone) || StringUtils.isEmpty(password)) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        UserModel userModel = userService.validateLogin(telphone, password);
        this.httpServletRequest.getSession().setAttribute("USER_LOGIN", true);
        this.httpServletRequest.getSession()
                .setAttribute("USER", convertFromModel(userModel));
        return CommonReturnType.creat(null);
    }

    //用户注册接口
    @RequestMapping("/regist")
    @ResponseBody
    public CommonReturnType register(@RequestParam(name = "telphone") String telphone,
                                     @RequestParam(name = "otpCode") String otpCode,
                                     @RequestParam(name = "name") String name,
                                     @RequestParam(name = "gender") Integer gender,
                                     @RequestParam(name = "age") Integer age,
                                     @RequestParam(name = "password") String pwd) throws BusinessException {
        String code = (String) this.httpServletRequest.getSession().getAttribute(telphone);
        if (!StringUtils.equals(otpCode, code)) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "短信验证码错误");
        }
        UserModel userModel = new UserModel();
        userModel.setName(name);
        userModel.setAge(age);
        userModel.setGender(gender);
        userModel.setRegisterMode("byphoone");
        userModel.setTelphone(telphone);
        userModel.setEncrptPassword(pwd);
        userService.register(userModel);
        return CommonReturnType.creat(null);
    }

    @RequestMapping(value = "/getotp", method = RequestMethod.POST, consumes = CONTENT_TYPE_FORMED)
    @ResponseBody
    public CommonReturnType getOtp(@RequestParam(name = "telphone", defaultValue = "12345678911") String telphone) {
        Random random = new Random();
        int randomInt = random.nextInt(900000) + 100000;
        //将otp验证码与用户手机关联，使用httpSession的方式进行绑定
        log.info("手机：{} + 验证码：{}", telphone, randomInt);
        httpServletRequest.getSession().setAttribute(telphone, randomInt + "");

        return CommonReturnType.creat(randomInt);
    }


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
