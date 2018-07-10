package com.wang.service;

import com.wang.entity.Permission;
import com.wang.entity.RolePermission;
import com.wang.entity.User;
import com.wang.entity.vo.PageVo;

import java.util.List;

public interface PermissionService {
    /**
     * 根据账号获取该账号的权限
     *
     * @param user
     * @return List
     */
    List<Permission> getPermissionListByUser(User user);

    List<RolePermission> getRolePermissionPageList(PageVo pageVo);

    Permission getPermissionById(String id);
}
