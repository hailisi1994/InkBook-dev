package com.hls.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author liangyehao
 * @version 1.0
 * @date 2020/3/15 11:01
 * @content
 */
public class BookUtil {
    /**
     * 根据当前时间获取Id
     *
     * @return {@link String}
     */
    public static String getIdByCurrentTime(){
        String format = "yyyyMMddHHmmssSSS";
        return new SimpleDateFormat(format) .format(new Date() );
    }

    /**
     * 条件like处理
     *
     * @param condition 条件
     * @return {@link String}
     */
    public static String conditionLike(String condition){
        if (condition==null) {
            condition = "";
        }
        return  "%"+condition+"%";
    }

    public static void main(String[] args) {

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(getIdByCurrentTime());
    }
}
