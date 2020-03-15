package com.hls.dao;

import com.hls.pojo.vo.BooksVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author liangyehao
 * @version 1.0
 * @date 2020/3/15 22:18
 * @content 自定义dao
 */
@Repository
public interface CustomDao {

    /**
     * 根据用户id查询借书信息
     *
     * @param userId 用户Id
     * @return {@link List<BooksVO>}
     */
    List<BooksVO> getBooksByUserId(@Param("userId") String userId);
}
