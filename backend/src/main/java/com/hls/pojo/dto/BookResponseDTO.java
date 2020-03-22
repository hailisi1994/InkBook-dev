package com.hls.pojo.dto;

import com.hls.pojo.Book;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author liangyehao
 * @version 1.0
 * @date 2020/3/22 17:18
 * @content 图书查询响应dto
 */
@Data
@Accessors(chain = true)
public class BookResponseDTO {

    private List<Book> bookList;

    private Pagination pagination;
}
