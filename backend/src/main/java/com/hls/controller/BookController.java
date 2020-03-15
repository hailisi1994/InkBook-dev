package com.hls.controller;

import com.hls.pojo.Book;
import com.hls.service.BookService;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;

/**
 * (Book)表控制层
 *
 * @author liangyehao
 * @since 2020-03-15 13:01:18
 */
@Slf4j
@Api(value = "", tags = "", description = "")
@RestController
@RequestMapping("book")
public class BookController {
    /**
     * 服务对象
     */
    @Resource
    private BookService bookService;

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
    public Book selectOne(@RequestParam String id) {
        return this.bookService.queryById(id);
    }

}