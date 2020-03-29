package com.hls.service.impl;

import com.hls.dao.BookDao;
import com.hls.pojo.Book;
import com.hls.pojo.dto.BookQueryDTO;
import com.hls.pojo.dto.BookResponseDTO;
import com.hls.pojo.dto.Pagination;
import com.hls.service.BookService;
import com.hls.utils.BookUtil;
import com.hls.utils.PaginationUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

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
    public Book  queryById(String id) {
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

    /**
     * 列表
     *
     * @param bookQueryDTO 书
     * @return {@link List<Book>}
     */
    @Override
    public BookResponseDTO list(BookQueryDTO bookQueryDTO) {
        Book book = bookQueryDTO.getBook();
        Pagination pagination = bookQueryDTO.getPagination();
        RowBounds rowBounds = PaginationUtil.createRowBoundsByPagination(pagination);
        Example example = new Example(Book.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("ifOn",1);
        if (book!=null) {
            if (StringUtils.isNotBlank(book.getSort())){
                criteria.andEqualTo("sort",book.getSort());
            }
            if (StringUtils.isNotBlank(book.getAuthor())){
                criteria.andLike("author",BookUtil.conditionLike(book.getAuthor()));
            }
            if (StringUtils.isNotBlank(book.getTitle())){
                criteria.andLike("title",BookUtil.conditionLike(book.getTitle()));
            }
        }
        example.orderBy("createTime").desc();
        //查询总记录
        int count = bookDao.selectCountByExample(example);
        pagination.setTotal(count);
        List<Book> bookList =  bookDao.selectByExampleAndRowBounds(example,rowBounds);
        return new BookResponseDTO().setBookList(bookList).setPagination(pagination);
    }


}