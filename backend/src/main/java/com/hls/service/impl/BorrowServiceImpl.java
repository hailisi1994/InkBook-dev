package com.hls.service.impl;

import com.hls.dao.BookDao;
import com.hls.dao.CustomDao;
import com.hls.dao.UserDao;
import com.hls.pojo.Book;
import com.hls.pojo.Borrow;
import com.hls.dao.BorrowDao;
import com.hls.pojo.vo.BooksVO;
import com.hls.pojo.vo.BorrowCountVO;
import com.hls.pojo.vo.MineVO;
import com.hls.service.BorrowService;
import com.hls.service.UserService;
import com.hls.utils.DateUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * (Borrow)表服务实现类
 *
 * @author liangyehao
 * @since 2020-03-15 13:22:49
 */
@Service("borrowService")
public class BorrowServiceImpl implements BorrowService {
    @Resource
    private BorrowDao borrowDao;

    @Resource
    private CustomDao customDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Borrow queryById(String id) {
        return this.borrowDao.selectByPrimaryKey(id);
    }


    /**
     * 新增数据
     *
     * @param borrow 实例对象
     * @return 实例对象
     */
    @Override
    public Borrow insert(Borrow borrow) {
        borrow.setId(UUID.randomUUID().toString());
        borrow.setBorrowTime(new Date());
        borrow.setIfReturn(0);
        //deadline默认10天后
        borrow.setDeadline(DateUtil.calDate(10));
        this.borrowDao.insert(borrow);
        return borrow;
    }

    /**
     * 修改数据
     *
     * @param borrow 实例对象
     * @return 实例对象
     */
    @Override
    public Borrow update(Borrow borrow) {
        this.borrowDao.updateByPrimaryKey(borrow);
        return this.queryById(borrow.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.borrowDao.deleteByPrimaryKey(id) > 0;
    }

    /**
     * 根据用户id查询借书信息
     *
     * @param userId 用户Id
     * @return {@link MineVO}
     */
    @Override
    public MineVO getBooksByUserId(String userId) {
        MineVO mineVO = new MineVO();
        BorrowCountVO counts = new BorrowCountVO();
        List<BooksVO> books = customDao.getBooksByUserId(userId);
        counts.setBorrowCount(books.size());
        int expiring = 0;
        int expired = 0;
        for (BooksVO book : books) {
            if (book.getLeftDays()<0){
                expired++;
            }else if (book.getLeftDays()<=3){
                expiring++;
            }
        }
        counts.setExpiringCount(expiring);
        counts.setExpiredCount(expired);
        mineVO.setBooks(books);
        mineVO.setCounts(counts);
        return mineVO;
    }

}