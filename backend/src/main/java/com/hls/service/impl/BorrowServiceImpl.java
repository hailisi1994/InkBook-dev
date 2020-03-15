package com.hls.service.impl;

import com.hls.pojo.Borrow;
import com.hls.dao.BorrowDao;
import com.hls.service.BorrowService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Borrow> queryAllByLimit(int offset, int limit) {
//        return this.borrowDao.queryAllByLimit(offset, limit);
        return null;
    }

    /**
     * 新增数据
     *
     * @param borrow 实例对象
     * @return 实例对象
     */
    @Override
    public Borrow insert(Borrow borrow) {
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
}