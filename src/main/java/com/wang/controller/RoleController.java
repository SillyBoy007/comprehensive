package com.wang.controller;

import com.wang.entity.Role;
import com.wang.entity.User;
import com.wang.entity.UserRole;
import com.wang.entity.vo.PageList;
import com.wang.entity.vo.PageVo;
import com.wang.service.RoleService;
import com.wang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;

    @RequestMapping("/userrole")
    @ResponseBody
    public PageList<Map> getUserRole(PageVo pageVo){
        try {
            List<UserRole> userRolePageList = roleService.getUserRolePageList(pageVo);
            PageList<Map> pageList = new PageList<Map>();
            List<Map> ret = new ArrayList<Map>();
            for (UserRole userRole:userRolePageList){
                Map<String,Object> retMap = new HashMap<String, Object>();
                User user = userService.getUserById(userRole.getUserid());
                Role role = roleService.getRoleById(userRole.getRoleid());
                retMap.put("id",userRole.getId());
                retMap.put("username",user.getUsername());
                retMap.put("role", role.getRole());
                ret.add(retMap);
            }
            pageList.setData(ret);
            pageList.setMsg("ok");
            pageList.setCode("0");
            pageList.setCount(roleService.countUserRole());
            return pageList;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }


}
