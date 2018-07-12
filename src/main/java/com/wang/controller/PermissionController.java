package com.wang.controller;

import com.wang.entity.Permission;
import com.wang.entity.Role;
import com.wang.entity.RolePermission;
import com.wang.entity.vo.PageList;
import com.wang.entity.vo.PageVo;
import com.wang.entity.vo.RetResult;
import com.wang.entity.vo.RolePermissionVo;
import com.wang.service.PermissionService;
import com.wang.service.RoleService;
import com.wang.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
        pageList.setCount(permissionService.countRolePermission());
        return pageList;
    }

    @RequestMapping("/addRolePermission")
    @ResponseBody
    public RetResult addRolePermission(RolePermissionVo rolePermissionVo){
        try {
            RetResult retResult = new RetResult();
            Permission permissionByPermissionByName = permissionService.getPermissionByName(rolePermissionVo.getPermission());
            if (StringUtils.isEmpty(permissionByPermissionByName)){
                retResult.setCode(1);
                retResult.setMsg("不存在该权限");
                return retResult;
            }
            Role roleByName = roleService.getRoleByName(rolePermissionVo.getRole());
            if (StringUtils.isEmpty(roleByName)){
                retResult.setCode(2);
                retResult.setMsg("不存在该角色");
                return retResult;
            }
            RolePermission rolePermission = new RolePermission();
            if (!StringUtils.isEmpty(permissionByPermissionByName) && !StringUtils.isEmpty(roleByName)){
                String permissionid = permissionByPermissionByName.getId();
                String roleid = roleByName.getId();
                rolePermission.setPermissionid(permissionid);
                rolePermission.setRoleid(roleid);
                rolePermission.setId(CommonUtils.getUUID());
            }
            permissionService.addRolePermission(rolePermission);
            retResult.setCode(0);
            retResult.setMsg("添加成功");
            return retResult;
        }catch (Exception e){
            e.printStackTrace();
            return RetResult.errorRet(-1,e.getMessage());
        }
    }

    @RequestMapping("/delRolePermission")
    @ResponseBody
    public RetResult delRolePermission(@RequestParam("id") String id){
        RetResult retResult = new RetResult();
        try {
            permissionService.delRolePermission(id);
            retResult.setMsg("删除成功");
            retResult.setCode(0);
            return retResult;
        }catch (Exception e) {
            e.printStackTrace();
            retResult.setCode(-1);
            retResult.setMsg(e.getMessage());
            return retResult;
        }
    }
}
