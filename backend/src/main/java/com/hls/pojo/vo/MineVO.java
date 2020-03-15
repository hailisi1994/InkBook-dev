package com.hls.pojo.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author liangyehao
 * @version 1.0
 * @date 2020/3/15 13:42
 * @content 我的
 */
@Data
public class MineVO implements Serializable {

    /**
     * 计数
     */
    private BorrowCountVO counts;

    /**
     * 我的借阅的书
     */
    private List<BooksVO> books;

}
