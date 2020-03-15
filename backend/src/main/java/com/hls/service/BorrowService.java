package com.hls.service;

import com.hls.pojo.Borrow;
import com.hls.pojo.vo.MineVO;

import java.util.List;

/**
 * (Borrow)表服务接口
 *
 * @author liangyehao
 * @since 2020-03-15 13:22:49
 */
public interface BorrowService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Borrow queryById(String id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Borrow> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param borrow 实例对象
     * @return 实例对象
     */
    Borrow insert(Borrow borrow);

    /**
     * 修改数据
     *
     * @param borrow 实例对象
     * @return 实例对象
     */
    Borrow update(Borrow borrow);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

    /**
     * 根据用户id查询借书信息
     *
     * @param userId 用户Id
     * @return {@link MineVO}
     */
    MineVO getBooksByUserId(String userId);
}