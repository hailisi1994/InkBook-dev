package com.hls.dao;


import com.hls.pojo.Book;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * (Book)表数据库访问层
 *
 * @author liangyehao
 * @since 2020-03-15 13:01:18
 */
@Repository
public interface BookDao extends Mapper<Book> {


}