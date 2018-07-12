package com.wang.service.Impl;

import com.wang.entity.Role;
import com.wang.entity.RoleExample;
import com.wang.entity.UserRole;
import com.wang.entity.UserRoleExample;
import com.wang.entity.vo.PageVo;
import com.wang.entity.vo.RetResult;
import com.wang.mapper.RoleMapper;
import com.wang.mapper.UserRoleMapper;
import com.wang.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

    public RetResult setManage(String id) {
        UserRole userRole = userRoleMapper.selectByPrimaryKey(id);

        if ("2".equals(userRole.getRoleid())){
            RetResult retResult = new RetResult();
            userRole.setRoleid("1");
            userRole.setId(id);
            userRoleMapper.updateByPrimaryKeySelective(userRole);
            retResult.setCode(0);
            retResult.setMsg("设置成功");
            return retResult;
        }else{
           RetResult retResult = new RetResult();
           retResult.setMsg("已经是管理员了");
           retResult.setCode(1);
           return retResult;
        }
    }

    public RetResult unSetManage(String id) {
        UserRole userRole = userRoleMapper.selectByPrimaryKey(id);

        if ("1".equals(userRole.getRoleid())){
            RetResult retResult = new RetResult();
            userRole.setRoleid("2");
            userRole.setId(id);
            userRoleMapper.updateByPrimaryKeySelective(userRole);
            retResult.setCode(0);
            retResult.setMsg("取消成功");
            return retResult;
        }else{
            RetResult retResult = new RetResult();
            retResult.setMsg("该用户不是管理员");
            retResult.setCode(1);
            return retResult;
        }
    }

    public Role getRoleByName(String role) {
        RoleExample roleExample = new RoleExample();
        RoleExample.Criteria criteria = roleExample.createCriteria();
        criteria.andRoleEqualTo(role);
        List<Role> roles = roleMapper.selectByExample(roleExample);
        if (!StringUtils.isEmpty(roles) && roles.size()>0){
            return roles.get(0);
        }
        return null;
    }
}
