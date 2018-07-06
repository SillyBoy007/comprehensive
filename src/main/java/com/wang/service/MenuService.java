package com.wang.service;

import com.wang.entity.Menu;

import java.util.List;


public interface MenuService {
    Menu getMenuById(String perstrs);
    List<Menu> getMenuListByPid(String perstrs);
}
