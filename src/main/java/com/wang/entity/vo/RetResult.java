package com.wang.entity.vo;

import java.util.Map;

/**
 * 返回结果类
 */
public class RetResult {
   private String msg;
   private int code;

   private Map<String,Object> data;



    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }





    public static RetResult successRet(Map<String,Object> map){
        RetResult retResult = new RetResult();
        retResult.setCode(0);
        retResult.setMsg("ok");
        retResult.setData(map);
        return  retResult;
    }
    public static RetResult errorRet(int code,String msg){
        RetResult retResult = new RetResult();
        retResult.setCode(code);
        retResult.setMsg(msg);
        retResult.setData(null);
        return  retResult;
    }


    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
