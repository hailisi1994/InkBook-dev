package com.hls.pojo.dto;

import lombok.Data;

/**
 * @author liangyehao
 * @version 1.0
 * @date 2020/3/13 22:46
 * @content 图书评分
 */
@Data
public class Rating {

    /**
     * star_count : 3.5
     * value : 7.2
     * count : 13548
     * rating_info :
     */

    private double star_count;
    private double value;
    private int count;
    private String rating_info;

}
