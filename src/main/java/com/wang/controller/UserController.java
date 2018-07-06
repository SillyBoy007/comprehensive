package com.wang.controller;

import com.wang.entity.User;
import com.wang.entity.vo.PageList;
import com.wang.entity.vo.RetResult;
import com.wang.entity.vo.UserVo;
import com.wang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("/query")
    @ResponseBody
    public PageList<User> getUserList(UserVo userVo){
        PageList<User> userPageList = new PageList<User>();
        List<User> userListByVo = userService.getUserListByVo(userVo);
        userPageList.setCode("0");
        userPageList.setMsg("ok");
        userPageList.setData(userListByVo);
        userPageList.setCount(userService.countUserByVo(userVo));
        return userPageList;
    }

    @RequestMapping("doLocked")
    @ResponseBody
    public RetResult doUserLocked(){
        return null;
    }


}
