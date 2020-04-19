package com.hls.pojo.vo;

import com.hls.pojo.Book;
import com.hls.pojo.Borrow;
import com.hls.pojo.User;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author liangyehao
 * @version 1.0
 * @date 2020/3/29 10:39
 * @content 扫描借阅二维后展示的信息
 */
@Data
@Accessors(chain = true)
public class BorrowInfoVo {
    private User user;
    private Book book;
    private Borrow borrow;
}
