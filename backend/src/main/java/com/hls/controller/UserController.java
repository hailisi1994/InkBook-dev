package com.hls.controller;


import com.hls.pojo.User;
import com.hls.pojo.vo.ResponseEntity;
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
@Api(value = "用户接口", tags = "", description = "")
@RestController
@RequestMapping("/user")
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
    @ApiOperation("查询单个用户")
    @GetMapping("/selectOne")
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
    @ApiOperation("查询所有用户")
    @GetMapping("/all")
    public List<User> list() {
        return this.userService.queryAll(new User());
    }


    /**
     * 注册
     *
     * @param user 用户
     * @return {@link ResponseEntity}
     */
    @ApiOperation("用户注册")
    @PostMapping("/regist")
    public ResponseEntity regist(@RequestBody User user){
        if(user==null){
            return ResponseEntity.errorMap("注册信息不能为空!");
        }
        if(!user.getPassword().equals(user.getConfirmPwd())){
            return ResponseEntity.errorMap("密码不一致!");
        }
        this.userService.insert(user);
        return  ResponseEntity.ok();
    }

}