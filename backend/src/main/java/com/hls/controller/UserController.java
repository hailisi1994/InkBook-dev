package com.hls.controller;


import com.hls.pojo.User;
import com.hls.service.UserService;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import java.util.List;

/**
 * (User)表控制层
 *
 * @author liangyehao
 * @since 2020-03-14 17:08:49
 */
@Slf4j
@Api(value = "", tags = "", description = "")
@RestController
@RequestMapping("user")
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;

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
    public User selectOne(@RequestParam String id) {
        return this.userService.queryById(id);
    }

    /**
     * 查询所有数据
     *
     * @return 单条数据
     */
    @ApiOperation("")
    @GetMapping("all")
    public List<User> list() {
        return this.userService.queryAll(new User());
    }

}