package com.hls.pojo.dto;

import lombok.Data;

/**
 * @author liangyehao
 * @version 1.0
 * @date 2020/3/29 10:16
 * @content 扫描借书二维码
 */
@Data
public class BorrowInfoDTO {

    private String borrowId;

    private String userId;

    private String bookId;

}
