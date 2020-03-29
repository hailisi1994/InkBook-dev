package com.hls.pojo.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author liangyehao
 * @version 1.0
 * @date 2020/3/15 22:08
 * @content 借阅的书籍
 */
@Data
public class BooksVO {

    private String borrowId;
    private String bookId;
    private String title;
    private String coverUrl;
    private Date borrowTime;
    private Date deadline;
    private Integer leftDays;
}
