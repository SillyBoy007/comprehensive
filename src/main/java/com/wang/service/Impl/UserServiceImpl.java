package com.wang.service.Impl;

import com.wang.entity.User;
import com.wang.entity.UserExample;
import com.wang.entity.UserRole;
import com.wang.entity.vo.UserVo;
import com.wang.mapper.UserMapper;
import com.wang.mapper.UserRoleMapper;
import com.wang.service.UserService;
import com.wang.utils.CommonUtils;
import com.wang.utils.PasswordHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;

    public List<User> getUserListByVo(UserVo userVo) {
        List<User> users = null;
        if (!StringUtils.isEmpty(userVo)){
            if (!StringUtils.isEmpty(userVo.getPage()) && !StringUtils.isEmpty(userVo.getLimit())){
                userVo.setPage((userVo.getPage()-1) * userVo.getLimit());
                users = userMapper.selectUserListByVo(userVo);
            }
        }
        return users;
    }

    public int countUserByVo(UserVo userVo) {
        int i = userMapper.countUserByVo(userVo);
        return i;
    }
    
    public int saveUser(User user){
        PasswordHelper passwordHelper = new PasswordHelper();
        passwordHelper.encryptPassword(user);//密码加密
        String uuid = CommonUtils.getUUID();
        user.setId(uuid);
        user.setLocked(false);
        user.setCreatetime(CommonUtils.transDate(new Date()));
        UserRole userRole = new UserRole();
        userRole.setRoleid("2");
        userRole.setUserid(uuid);
        String uuid2 = CommonUtils.getUUID();
        userRole.setId(uuid2);
        int row = userMapper.insert(user);
        userRoleMapper.insert(userRole);
        return row;
    }


    public User findUserByName(String username){
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<User> users = userMapper.selectByExample(userExample);
        if (users != null && users.size()>0){
            return users.get(0);
        }
        return null;
    }

    public User getUserById(String id) {
        User user = userMapper.selectByPrimaryKey(id);
        return user;
    }

    public void doLocked(String id) {
        User user = new User();
        user.setId(id);
        user.setLocked(true);
        userMapper.updateByPrimaryKeySelective(user);
    }

}
