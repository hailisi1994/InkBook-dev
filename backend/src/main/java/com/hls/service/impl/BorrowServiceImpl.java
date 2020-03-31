package com.hls.service.impl;

import com.hls.dao.BookDao;
import com.hls.dao.CustomDao;
import com.hls.dao.UserDao;
import com.hls.pojo.Book;
import com.hls.pojo.Borrow;
import com.hls.dao.BorrowDao;
import com.hls.pojo.User;
import com.hls.pojo.dto.BorrowInfoDTO;
import com.hls.pojo.vo.*;
import com.hls.service.BookService;
import com.hls.service.BorrowService;
import com.hls.service.UserService;
import com.hls.utils.DateUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.*;

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

    @Resource
    private BookDao bookDao;

    @Resource
    private UserDao userDao;

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
    @Transactional(rollbackFor = Exception.class)
    public Borrow insert(Borrow borrow) {
        //1.更新book信息
        Book book = bookDao.selectByPrimaryKey(borrow.getBookId());
        book.setIfOn(0);
        bookDao.updateByPrimaryKey(book);
        //2.插入borrow记录
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

    /**
     * 扫描借阅信息
     *
     * @param borrowInfo 借信息
     * @return {@link BorrowInfoVo}
     */
    @Override
    public BorrowInfoVo scanBorrowInfo(BorrowInfoDTO borrowInfo) {
        Borrow borrow = borrowDao.selectByPrimaryKey(borrowInfo.getBorrowId());
        Book book = bookDao.selectByPrimaryKey(borrow.getBookId());
        User user = userDao.selectByPrimaryKey(borrow.getUserId());
        return new BorrowInfoVo().setBook(book).setUser(user).setBorrow(borrow);
    }

    /**
     * 饼图数据
     *
     * @return {@link Map <String, Object>}
     */
    @Override
    public Map<String, Object> getPieChartData() {
        List<ChartDataVo> pieChartData = customDao.getPieChartData();
        List<String> sortArray = new ArrayList<>();
        for (ChartDataVo pieChartDatum : pieChartData) {
            sortArray.add(pieChartDatum.getName());
        }
        Map<String, Object> result = new HashMap<>();
        result.put("sortArray",sortArray);
        result.put("pieChartData",pieChartData);
        return result;
    }

    /**
     * 返回的书
     *
     * @param borrowInfo 借信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class )
    public void returnBook(BorrowInfoDTO borrowInfo) {
        //1.更新borrow表
        Borrow borrow = borrowDao.selectByPrimaryKey(new Borrow().setId(borrowInfo.getBorrowId()));
        borrow.setReturnTime(new Date());
        borrow.setIfReturn(1);
        borrowDao.updateByPrimaryKey(borrow);

        //更新book表
        Book book = bookDao.selectByPrimaryKey(borrow.getBookId());
        book.setIfOn(1);
        bookDao.updateByPrimaryKey(book);
    }


}