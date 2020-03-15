package com.hls.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author liangyehao
 * @version 1.0
 * @date 2020/3/15 11:01
 * @content
 */
public class BookUtil {
    public static String getIdByCurrentTime(){
        String format = "yyyyMMddHHmmssSSS";
        return new SimpleDateFormat(format) .format(new Date() );
    }

    public static void main(String[] args) {

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(getIdByCurrentTime());
    }
}
