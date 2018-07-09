package com.wang.entity.vo;

import java.util.List;

/**
 * 返回layui分页数据类
 * @param <T>
 */
public class PageList<T> {
    private String msg;
    private String code;
    private List<T> data;
    private int count;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
