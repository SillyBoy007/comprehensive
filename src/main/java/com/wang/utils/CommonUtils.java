package com.wang.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
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

    /**
     * 获取 properties
     * @return
     * @throws IOException
     */
    public static String getProperties(String key) throws IOException {
        Properties properties = new Properties();
        InputStream resourceAsStream = CommonUtils.class.getClassLoader().getResourceAsStream("resource.properties");
        properties.load(resourceAsStream);
        String property = properties.getProperty(key);
        return property;
    }



}