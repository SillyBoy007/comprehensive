package com.wang.controller;

import com.wang.entity.Role;
import com.wang.entity.User;
import com.wang.entity.UserRole;
import com.wang.entity.vo.PageList;
import com.wang.entity.vo.PageVo;
import com.wang.entity.vo.RetResult;
import com.wang.service.RoleService;
import com.wang.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/role")
public class RoleController {
    protected static Logger logger = Logger.getLogger(RoleController.class);
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;

    @RequestMapping("/userRole")
    @ResponseBody
    public PageList<Map> getUserRole(PageVo pageVo){
        try {
            List<UserRole> userRolePageList = roleService.getUserRolePageList(pageVo);
            logger.info("查询到用户角色的条数"+userRolePageList.size());
            PageList<Map> pageList = new PageList<Map>();
            List<Map> ret = new ArrayList<Map>();
            for (UserRole userRole:userRolePageList){
                Map<String,Object> retMap = new HashMap<String, Object>();
                //logger.info("userId:"+userRole.getUserid());
                User user = userService.getUserById(userRole.getUserid());
                Role role = roleService.getRoleById(userRole.getRoleid());
                retMap.put("id",userRole.getId());
                retMap.put("username",user.getUsername());
                retMap.put("role", role.getRole());
                ret.add(retMap);
            }
            pageList.setData(ret);
            pageList.setMsg("ok");
            pageList.setCode("0");
            pageList.setCount(roleService.countUserRole());
            return pageList;
        }catch (Exception e){
            logger.error("错误:"+e);
            return null;
        }
    }

    @RequestMapping("/setManage")
    @ResponseBody
    public RetResult setManage(String id){
        try {
            RetResult retResult = roleService.setManage(id);
            if (!StringUtils.isEmpty(retResult)){
                return retResult;
            }else {
                 retResult = new RetResult();
                 retResult.setMsg("null");
                 retResult.setCode(1);
                 return retResult;
            }

        }catch (Exception e){
            e.printStackTrace();
            RetResult  retResult = new RetResult();
            retResult.setMsg(e.getMessage());
            retResult.setCode(-1);
            return retResult;
        }

    }
    @RequestMapping("/unSetManage")
    @ResponseBody
    public RetResult unSetManage(String id){
        try {
            RetResult retResult = roleService.unSetManage(id);
            if (!StringUtils.isEmpty(retResult)){
                return retResult;
            }else {
                retResult = new RetResult();
                retResult.setMsg("没有数据");
                retResult.setCode(1);
                return retResult;
            }

        }catch (Exception e){
            e.printStackTrace();
            RetResult  retResult = new RetResult();
            retResult.setMsg(e.getMessage());
            retResult.setCode(-2);
            return retResult;
        }

    }

}
