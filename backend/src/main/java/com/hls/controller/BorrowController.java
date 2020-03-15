package com.hls.controller;

import com.hls.pojo.Borrow;
import com.hls.service.BorrowService;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;

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

}