package com.hls.pojo.dto;

import lombok.Data;

/**
 * @author liangyehao
 * @version 1.0
 * @date 2020/3/13 22:47
 * @content 图书评论
 */
@Data
public class Comments {

    /**
     * user_name : 飞林沙
     * user_page : https://www.douban.com/people/lovekym/
     * user_pic : https://img9.doubanio.com/icon/u3831809-15.jpg
     * vote : 100
     * rate : 30
     * time : 2011-10-05
     * content : 一句话足以概括全书，何苦要长篇大论呢
     */

    private String user_name;
    private String user_page;
    private String user_pic;
    private String vote;
    private String rate;
    private String time;
    private String content;
}
