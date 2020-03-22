package com.hls.enums;

/**
 * @author liangyehao
 * @version 1.0
 * @date 2020/3/15 22:55
 * @content
 */
public enum BookEnums {
    /**
     * 提醒时间
     * */
    WARNING("即将到期",3),
    /**
     * 过期时间
     * */
    DANGEROUS("已过期",0);

    private String type;
    private Integer day;

    BookEnums(String type, Integer day) {
        this.type = type;
        this.day = day;
    }

    public static void main(String[] args) {
        System.out.println(BookEnums.WARNING.day);
        System.out.println(BookEnums.DANGEROUS.type);
    }
}
