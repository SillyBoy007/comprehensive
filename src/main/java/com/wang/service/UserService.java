package com.wang.service;

import com.wang.entity.User;
import com.wang.entity.vo.UserVo;

import java.util.List;

public interface UserService {
    /**
     * 根据查询条件获取用户列表，并分页返回
     * @param userVo 用户查询条件
     * @return 分页后的数据
     */
    List<User> getUserListByVo(UserVo userVo);

    /**
     * 根据条件统计用户的总数
     * @param userVo 用户查询条件
     * @return 用户的总数量
     */
    int countUserByVo(UserVo userVo);

    /**
     * 保存用户
     * @param user 用户的信息
     * @return
     */
    int saveUser(User user);
    /**
     * 根据账号获取账号密码
     * @param username 用户名称
     * @return UserPojo 用户
     */
    User findUserByName(String username);

    /**
     *根据用户ID获取用户
     * @param id 用户ID
     * @return 用户
     */
    User getUserById(String id);

    /**
     * 根据ID锁定用户
     * @param id 用户ID
     */
    void doLocked(String id);

}
