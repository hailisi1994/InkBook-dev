package com.hls.service;

import com.hls.pojo.Borrow;
import com.hls.pojo.dto.BorrowInfoDTO;
import com.hls.pojo.vo.BorrowInfoVo;
import com.hls.pojo.vo.MineVO;

import java.util.List;
import java.util.Map;

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

    /**
     * 扫描借阅信息
     *
     * @param borrowInfo 借信息
     * @return {@link BorrowInfoVo}
     */
    BorrowInfoVo scanBorrowInfo(BorrowInfoDTO borrowInfo);

    /**
     * 饼图数据
     *
     * @return {@link Map<String, Object>}
     */
    Map<String,Object> getPieChartData();

    /**
     * 返回的书
     *
     * @param borrowInfo 借信息
     */
    void returnBook(BorrowInfoDTO borrowInfo);
}