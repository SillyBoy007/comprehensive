package com.wang.entity.vo;


import java.io.Serializable;

/**
 * 查询用户vo类
 */
public class UserVo extends PageVo  implements Serializable {
    private String  username;


    public UserVo() {}


    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }


}
