package com.wang.service;

import com.wang.entity.User;
import com.wang.entity.vo.UserVo;

import java.util.List;

public interface UserService {
    List<User> getUserListByVo(UserVo userVo);
    int countUserByVo(UserVo userVo);
    int saveUser(User user);
    /**
     * 根据账号获取账号密码
     * @param username
     * @return UserPojo
     */
    User findUserByName(String username);

    User getUserById(String id);

}
