package com.hls.pojo.dto;

import com.hls.pojo.Book;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.ibatis.session.RowBounds;

/**
 * @author liangyehao
 * @version 1.0
 * @date 2020/3/22 15:57
 * @content 查询图书dto
 */
@Data
@Accessors(chain = true)
public class BookQueryDTO {

    private Book book;

    private Pagination pagination;

}
