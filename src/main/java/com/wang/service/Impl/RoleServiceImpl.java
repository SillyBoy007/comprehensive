package com.wang.service.Impl;

import com.wang.entity.Role;
import com.wang.entity.UserRole;
import com.wang.entity.UserRoleExample;
import com.wang.entity.vo.PageVo;
import com.wang.mapper.RoleMapper;
import com.wang.mapper.UserRoleMapper;
import com.wang.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private RoleMapper roleMapper;
    public List<UserRole> getUserRolePageList(PageVo pageVo) {
        pageVo.setPage((pageVo.getPage()-1)*pageVo.getLimit());
        List<UserRole> userRoles = userRoleMapper.selectUserRolePageList(pageVo);
        return userRoles;
    }

    public Role getRoleById(String id) {
        Role role = roleMapper.selectByPrimaryKey(id);
        return role;
    }

    public int countUserRole() {
        UserRoleExample userRoleExample = new UserRoleExample();
        int i = userRoleMapper.countByExample(userRoleExample);
        return i;
    }
}
