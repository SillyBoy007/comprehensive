package com.wang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class PageController {

    @RequestMapping(value = {"/","/index"})
    public String toIndex()
    {
        return "index";
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String toLogin(HttpSession session){
        if (!StringUtils.isEmpty(session.getAttribute("username"))){
            return "redirect:index";
        }else {
            return "/views/login";
        }

    }

    @RequestMapping(value = "/page/error",method = RequestMethod.GET)
    public String toErrorPage(){
        return "/views/404";
    }
    @RequestMapping(value = "/author/user",method = RequestMethod.GET)
    public String toUserPage(){
        return "/views/user";
    }
    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String toRegisterPage(HttpSession session){
        if (!StringUtils.isEmpty(session.getAttribute("username"))){
            return "redirect:index";
        }else {
            return "/views/register";
        }
    }
    @RequestMapping(value = "/nopermission",method = RequestMethod.GET)
    public String toNopermissionrPage(){
        return "/views/nopermission";
    }

    @RequestMapping(value = "/module/order",method = RequestMethod.GET)
    public String toOrderPage(){
        return "/views/order";
    }

    @RequestMapping(value = "/practice/upload",method = RequestMethod.GET)
    public String toFileUploadPage(){
        return "/views/fileupload";
    }

    @RequestMapping(value = "/author/role",method = RequestMethod.GET)
    public String toRolePage(){
        return "/views/role";
    }

    @RequestMapping(value = "/author/permission",method = RequestMethod.GET)
    public String toPermissionPage(){
        return "/views/permission";
    }
}
