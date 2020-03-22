package com.hls.pojo.dto;

import io.swagger.models.auth.In;
import lombok.Data;

/**
 * @author liangyehao
 * @version 1.0
 * @date 2020/3/22 16:49
 * @content
 */
@Data
public class Pagination {
    private Integer page;
    private Integer pageSize;
    private Integer total;
}
