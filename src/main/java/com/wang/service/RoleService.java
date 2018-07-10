package com.wang.service;

import com.wang.entity.Role;
import com.wang.entity.UserRole;
import com.wang.entity.vo.PageVo;
import com.wang.entity.vo.RetResult;

import java.util.List;

public interface RoleService {
    /**
     * 根据分页信息分页返回用户角色
     * @param pageVo 分页信息
     * @return
     */
    List<UserRole> getUserRolePageList(PageVo pageVo);

    /**
     * 根据角色ID返回角色
     * @param id 角色ID
     * @return
     */
    Role getRoleById(String id);

    /**
     * 统计用户角色数量
     * @return
     */
    int countUserRole();

    /**
     * 设置管理
     * @param id 用户角色ID
     * @return
     */
    RetResult setManage(String id);

    /**
     * 撤销管理
     * @param id  用户角色ID
     * @return
     */
    RetResult unSetManage(String id);
}
