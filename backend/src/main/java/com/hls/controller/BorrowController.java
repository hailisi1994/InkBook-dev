package com.hls.controller;

import com.hls.dao.CustomDao;
import com.hls.pojo.Book;
import com.hls.pojo.Borrow;
import com.hls.pojo.dto.BorrowInfoDTO;
import com.hls.pojo.vo.BorrowInfoVo;
import com.hls.pojo.vo.MineVO;
import com.hls.pojo.vo.ResponseEntity;
import com.hls.service.BorrowService;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import javax.annotation.Resource;
import java.util.List;

/**
 * (Borrow)表控制层
 *
 * @author liangyehao
 * @since 2020-03-15 13:22:49
 */
@Slf4j
@Api(value = "", tags = "", description = "")
@RestController
@RequestMapping("borrow")
public class BorrowController {
    /**
     * 服务对象
     */
    @Resource
    private BorrowService borrowService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @ApiOperation("")
    @GetMapping("selectOne")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "", required = true, paramType = "query", dataType = "String", example = "0"),
    })
    public Borrow selectOne(@RequestParam String id) {
        return this.borrowService.queryById(id);
    }


    /**
     * 根据用户id查询借书信息
     *
     * @param userId 用户主键
     * @return 借书信息
     */
    @ApiOperation("根据用户id查询借书信息")
    @GetMapping("/getBooksByUserId/{userId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "", required = true, paramType = "query", dataType = "String", example = "0"),
    })
    public ResponseEntity getMyBooks(@PathVariable("userId") String userId) {
        MineVO mineVO = borrowService.getBooksByUserId(userId);
        return ResponseEntity.okMap(mineVO);
    }


    /**
     * 添加借阅记录
     *
     * @param borrow 借
     * @return {@link Borrow}
     */
    @ApiOperation("添加借阅记录")
    @PostMapping("/save")
    @ApiImplicitParams({})
    public ResponseEntity add(@RequestBody Borrow borrow) {
        Borrow save = borrowService.insert(borrow);
        return ResponseEntity.okMap(save);
    }

    /**
     * 扫描借信息
     *
     * @param borrowInfo 扫描得到的信息
     * @return {@link ResponseEntity}
     */
    @ApiOperation("扫描得到的信息")
    @PostMapping("/scanBorrowInfo")
    @ApiImplicitParams({})
    public ResponseEntity scanBorrowInfo(@RequestBody BorrowInfoDTO borrowInfo) {
        BorrowInfoVo borrowInfoVo = borrowService.scanBorrowInfo(borrowInfo);
        return ResponseEntity.okMap(borrowInfoVo);
    }


    /**
     * 获取统计饼图数据
     *
     * @return {@link ResponseEntity}
     */
    @ApiOperation("添加借阅记录")
    @GetMapping("/getPieChartData")
    @ApiImplicitParams({})
    public ResponseEntity getPieChartData() {
        return ResponseEntity.okMap(borrowService.getPieChartData());
    }

}