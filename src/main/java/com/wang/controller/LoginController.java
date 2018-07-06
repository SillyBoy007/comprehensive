package com.wang.controller;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2017/10/10.
 */
@Controller
public class LoginController {

    private Logger logger = Logger.getLogger(LoginController.class);


    /**
     * 验证登录
     * @param username
     * @param password
     * @param session
     * @return url
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String Login(String username, String password, HttpSession session, Model model){

        //主体,当前状态为没有认证的状态“未认证”
        Subject subject = SecurityUtils.getSubject();
        // 登录后存放进shiro token
        UsernamePasswordToken token=new UsernamePasswordToken(username,password);

        String message=null;
        try {
            //利用异常操作
            //需要开始调用到Realm中
            subject.login(token);
            String u = (String) subject.getPrincipal();
            session.setAttribute("username",u);

        } catch (UnknownAccountException e) {
            model.addAttribute("message","账号不正确");
            return "/views/login";
        } catch (IncorrectCredentialsException incorr){

            model.addAttribute("message","密码不正确");
            return "/views/login";
        }catch (LockedAccountException locke){
            model.addAttribute("message","该账户已经被锁定");
            return "/views/login";
        } catch (Exception e){
            model.addAttribute("message",e.getMessage());
            return "/views/login";
        }

        //登录方法（认证是否通过）
        //使用subject调用securityManager,安全管理器调用Realm
        return "redirect:index";
    }


}
