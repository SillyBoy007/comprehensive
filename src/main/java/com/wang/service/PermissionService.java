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

    /**
     * 根据分页信息分页返回角色权限列表
     * @param pageVo 分页信息
     * @return
     */
    List<RolePermission> getRolePermissionPageList(PageVo pageVo);

    /**
     * 根据权限ID获取权限
     * @param id 权限ID
     * @return
     */
    Permission getPermissionById(String id);

    /**
     * 统计角色权限数量
     * @return
     */
    int countRolePermission();
}
