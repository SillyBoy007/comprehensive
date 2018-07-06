package com.wang.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class CommonUtils {

    public static String transDate(Date date){
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
        String dateString = ft.format(date);
        return dateString;
    }
    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}