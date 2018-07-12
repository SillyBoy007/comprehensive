package com.wang.service;

import com.wang.entity.Menu;

import java.util.List;

public interface MenuService {
    /**
     * 根据permissionID获取菜单按钮
     * @param perstrs permissionID
     * @return
     */
    Menu getMenuById(String perstrs);

    /**
     * 根据pid获取菜单列表
     * @param perstrs pid
     * @return
     */
    List<Menu> getMenuListByPid(String perstrs);
}
