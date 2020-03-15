package com.hls.pojo.vo;

import lombok.Data;

/**
 * @author liangyehao
 * @version 1.0
 * @date 2020/3/15 22:09
 * @content
 */
@Data
public class BorrowCountVO {
    /**
     * 总数
     */
    private Integer totalCount;

    /**
     * 借书数
     */
    private Integer borrowCount;

    /**
     * 即将过期数
     */
    private Integer expiringCount;

    /**
     * 已过期数
     */
    private Integer expiredCount;

}
