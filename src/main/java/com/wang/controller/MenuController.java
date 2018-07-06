package com.wang.controller;

import com.wang.entity.Permission;
import com.wang.entity.User;
import com.wang.service.MenuService;

import com.wang.service.PermissionService;
import com.wang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class MenuController {

    @Autowired
    private MenuService menuService;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private UserService userService;

    @RequestMapping("/menu/list")
    @ResponseBody
    public List<Map<String,Object>> getMenuList(HttpSession session){

        List<Map<String,Object>> result = new ArrayList<Map<String, Object>>();
        String username = (String) session.getAttribute("username");
        User u = userService.findUserByName(username);
        List<Permission> permissions = permissionService.getPermissionListByUser(u);
        List<String> pids = new ArrayList<String>();
        for (Permission permission: permissions){
            String pid = permission.getPid();
            pids.add(pid);
        }

        try {
            for (String pid:pids){
                Map<String,Object> map = new HashMap<String, Object>();
                map.put("parent",menuService.getMenuById(pid));
                map.put("childs",menuService.getMenuListByPid(pid));
                result.add(map);
            }

        } catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
