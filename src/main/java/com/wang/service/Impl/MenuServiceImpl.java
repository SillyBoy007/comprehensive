package com.wang.service.Impl;

import com.wang.entity.Menu;
import com.wang.entity.MenuExample;
import com.wang.mapper.MenuMapper;
import com.wang.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuMapper menuMapper;

    public Menu getMenuById(String id) {

        Menu menu = menuMapper.selectByPrimaryKey(id);

        return menu;
    }
    public List<Menu> getMenuListByPid(String pid) {

        MenuExample menuExample = new MenuExample();
        MenuExample.Criteria criteria = menuExample.createCriteria();
        criteria.andPidEqualTo(pid);
        List<Menu>  menus = menuMapper.selectByExample(menuExample);

        return menus;
    }
}

