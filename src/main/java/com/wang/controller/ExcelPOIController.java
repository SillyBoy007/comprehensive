package com.wang.controller;

import com.alibaba.fastjson.JSONArray;
import com.wang.entity.User;
import com.wang.utils.ExcelUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ExcelPOIController {
    @RequestMapping(value = "/doExport")
    @ResponseBody
    public void exportExcelData(HttpServletRequest request, HttpServletResponse response, @RequestParam String userList){
        List<User> mgUsers = JSONArray.parseArray(userList,User.class);

        // ReturnResult returnResult = new ReturnResult();
        // 定义表的标题
        String title = "用户列表";
        //定义表的列名
        String[] rowsName = new String[] { "序号","ID" ,"用户名", "密码", "盐值", "创建时间", "是否锁定" };
        //定义表的内容
        List<Object[]> dataList = new ArrayList<Object[]>();

        for (int i=0;i<mgUsers.size();i++){
            Object[] objects = new Object[rowsName.length];
            objects[1] = mgUsers.get(i).getId();
            objects[2] = mgUsers.get(i).getUsername();
            objects[3] = mgUsers.get(i).getPassword();
            objects[4] = mgUsers.get(i).getSalt();
            objects[5] = mgUsers.get(i).getCreatetime();
            objects[6] = mgUsers.get(i).getLocked();
            dataList.add(objects);
        }

        try {
            String fileName= new String("用户数据表.xls".getBytes("UTF-8"),"iso-8859-1");    //生成word文件的文件名
            ExcelUtil.exportExcel(title,rowsName,dataList,fileName,response);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
