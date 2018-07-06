package com.wang.controller;

import com.wang.entity.User;
import com.wang.entity.vo.RetResult;
import com.wang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RegisterController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ResponseBody
    public RetResult doRegister(User user){
        try {

            userService.saveUser(user);
            return RetResult.successRet(null);
        }catch (Exception e){
            e.printStackTrace();
            return RetResult.errorRet(-1,e.getMessage());
        }
    }
}
