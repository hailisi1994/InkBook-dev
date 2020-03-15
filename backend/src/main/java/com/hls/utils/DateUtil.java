package com.hls.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * @author liangyehao
 * @version 1.0
 * @date 2020/3/15 21:44
 * @content
 */
public class DateUtil {

    /**
     * 计算日期
     *
     * @param amount 时间差 7 代表7天后
     * @return {@link Date}
     */
    public static Date calDate(Integer amount){
        Date date=new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, amount);
        return calendar.getTime();
    }
}
