package com.hls.utils;

import com.hls.pojo.dto.Pagination;
import org.apache.ibatis.session.RowBounds;

/**
 * @author liangyehao
 * @version 1.0
 * @date 2020/3/22 17:00
 * @content 分页工具类
 */
public class PaginationUtil {
    public static RowBounds createRowBoundsByPagination(Pagination pagination){
        if (pagination!=null) {
            int offset = (pagination.getPage()-1)*pagination.getPageSize();
            int limit = pagination.getPageSize();
            return new RowBounds(offset,limit);
        }
        return new RowBounds();
    }
}
