package com.wang.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 常用方法工具类
 */
public class CommonUtils {
    /**
     * 转换时间日期  Date类转换为yyyy-MM-dd hh:mm:ss的String类
     * @param date
     * @return String
     */
    public static String transDate(Date date){
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
        String dateString = ft.format(date);
        return dateString;
    }

    /**
     * 获取UUID
     * @return String
     */
    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}