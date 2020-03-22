package com.hls.service.impl;

import com.hls.dao.BookDao;
import com.hls.pojo.Book;
import com.hls.service.BookService;
import com.hls.utils.BookUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * (Book)表服务实现类
 *
 * @author liangyehao
 * @since 2020-03-15 13:01:18
 */
@Service("bookService")
public class BookServiceImpl implements BookService {
    @Resource
    private BookDao bookDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Book queryById(String id) {
        return this.bookDao.selectByPrimaryKey(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Book> queryAllByLimit(int offset, int limit) {
//        return this.bookDao.queryAllByLimit(offset, limit);
        return null;
    }

    /**
     * 新增数据
     *
     * @param book 实例对象
     * @return 实例对象
     */
    @Override
    public Book insert(Book book) {
        book.setId(BookUtil.getIdByCurrentTime());
        book.setIfOn(1);
        book.setSort("默认");
        book.setCreateTime(new Date());
        book.setUpdateTime(new Date());
        this.bookDao.insert(book);
        return book;
    }

    /**
     * 修改数据
     *
     * @param book 实例对象
     * @return 实例对象
     */
    @Override
    public Book update(Book book) {
        this.bookDao.updateByPrimaryKey(book);
        return this.queryById(book.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.bookDao.deleteByPrimaryKey(id) > 0;
    }
}