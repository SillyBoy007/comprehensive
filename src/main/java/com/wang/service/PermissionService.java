package com.wang.service;

import com.wang.entity.Permission;
import com.wang.entity.User;

import java.util.List;

public interface PermissionService {
    /**
     * 根据账号获取该账号的权限
     *
     * @param user
     * @return List
     */
    List<Permission> getPermissionListByUser(User user);
}
