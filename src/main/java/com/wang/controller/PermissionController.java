package com.wang.controller;

import com.wang.entity.Permission;
import com.wang.entity.Role;
import com.wang.entity.RolePermission;
import com.wang.entity.vo.PageList;
import com.wang.entity.vo.PageVo;
import com.wang.entity.vo.RetResult;
import com.wang.service.PermissionService;
import com.wang.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;
    @Autowired
    private RoleService roleService;


    @RequestMapping("/getRolePermission")
    @ResponseBody
    public PageList<Map> getRolePermission(PageVo pageVo){
        PageList pageList = new PageList();
        List<RolePermission> rolePermissionPageList = permissionService.getRolePermissionPageList(pageVo);
        List<Map> list = new ArrayList<Map>();
        for (RolePermission rolePermission:rolePermissionPageList){
            Map map = new HashMap();
            Permission permissionById = permissionService.getPermissionById(rolePermission.getPermissionid());
            Role roleById = roleService.getRoleById(rolePermission.getRoleid());
            map.put("permission",permissionById.getPermission());
            map.put("id",rolePermission.getId());
            map.put("role",roleById.getRole());
            list.add(map);
        }
        pageList.setMsg("ok");
        pageList.setCode("0");
        pageList.setData(list);
        pageList.setCount(10);
        return pageList;
    }

    @RequestMapping("/addRolePermission")
    @ResponseBody
    public RetResult addRolePermission(RolePermission rolePermission){

        return null;
    }
}
