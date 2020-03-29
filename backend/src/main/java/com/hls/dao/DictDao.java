package com.hls.dao;

import com.hls.pojo.Dict;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author liangyehao
 * @version 1.0
 * @date 2020/3/29 16:57
 * @content
 */
@Repository
public interface DictDao extends Mapper<Dict> {
}
