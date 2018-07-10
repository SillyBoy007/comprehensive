package com.wang.service.Impl;

import com.wang.entity.*;
import com.wang.entity.vo.PageVo;
import com.wang.mapper.PermissionMapper;
import com.wang.mapper.RolePermissionMapper;
import com.wang.mapper.UserRoleMapper;
import com.wang.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    public  List<Permission> getPermissionListByUser(User user) {
        //获取到用户角色userRole
        List<String> roleIds = new ArrayList<String>();

        UserRoleExample userRoleExample = new UserRoleExample();
        UserRoleExample.Criteria criteria = userRoleExample.createCriteria();
        criteria.andUseridEqualTo(user.getId());
        List<UserRole> userRoles = userRoleMapper.selectByExample(userRoleExample);
        for (UserRole userRole : userRoles){
            roleIds.add(userRole.getRoleid());
        }
        List<Permission> permissions = new ArrayList<Permission>();
        List<RolePermission> rolePermissions = new ArrayList<RolePermission>();
        if (roleIds.size() != 0) {
            //根据roleid获取peimission
            for (String roleid : roleIds) {
                RolePermissionExample rolePermissionExample = new RolePermissionExample();
                RolePermissionExample.Criteria criteria1 = rolePermissionExample.createCriteria();
                criteria1.andRoleidEqualTo(roleid);
                List<RolePermission> rolePermissions1 = rolePermissionMapper.selectByExample(rolePermissionExample);
                rolePermissions.addAll(rolePermissions1);
            }
        }

        if (rolePermissions.size() != 0){
            for (RolePermission roleoer : rolePermissions) {
                String permissionid = roleoer.getPermissionid();
                Permission permission = permissionMapper.selectByPrimaryKey(permissionid);
                permissions.add(permission);
            }
        }

        return permissions;
    }

    public List<RolePermission> getRolePermissionPageList(PageVo pageVo) {
        pageVo.setPage((pageVo.getPage()-1)*pageVo.getLimit());
        List<RolePermission> rolePermissions = rolePermissionMapper.selectRolePermissionByPageList(pageVo);
        return rolePermissions;
    }

    public Permission getPermissionById(String id) {
        return permissionMapper.selectByPrimaryKey(id);
    }

    public int countRolePermission() {
        RolePermissionExample rolePermissionExample = new RolePermissionExample();
        return rolePermissionMapper.countByExample(rolePermissionExample);
    }


}
