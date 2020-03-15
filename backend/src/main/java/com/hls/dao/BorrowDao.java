package com.hls.dao;

import com.hls.pojo.Book;
import com.hls.pojo.Borrow;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * (Borrow)表数据库访问层
 *
 * @author liangyehao
 * @since 2020-03-15 13:22:49
 */
@Repository
public interface BorrowDao extends Mapper<Borrow> {

}