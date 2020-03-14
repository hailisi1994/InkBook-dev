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
            return ResponseEntity.errorMsg("注册信息不能为空!");
        }
        if(!user.getPassword().equals(user.getConfirmPwd())){
            return ResponseEntity.errorMsg("密码不一致!");
        }
        if (userService.checkExist(user.getPhone())){
            return ResponseEntity.errorMsg("用户已存在!");
        }
        this.userService.insert(user);
        return  ResponseEntity.okMap(user);
    }

    /**
     * 登录
     *
     * @param user 用户
     * @return {@link ResponseEntity}
     */
    @ApiOperation("用户登录")
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody User user){
        if(user==null){
            return ResponseEntity.errorMsg("用户信息不能为空!");
        }
        List<User> check = userService.check(user.getPhone(), user.getPassword());
        if (check.size()>0){
            return  ResponseEntity.okMap(check.get(0));
        }
        return  ResponseEntity.errorMsg("登录失败!");
    }


    /**
     * 检查用户是否存在
     *
     * @param user 用户
     * @return {@link ResponseEntity}
     */
    @ApiOperation("检查用户是否存在")
    @PostMapping("/checkExist")
    public ResponseEntity checkExist(@RequestBody User user){
        if(user==null){
            return ResponseEntity.errorMsg("用户信息不能为空!");
        }
        return  ResponseEntity.okMap(userService.checkExist(user.getPhone()));
    }


    /**
     * 更新用户
     *
     * @param user 用户
     * @return {@link ResponseEntity}
     */
    @ApiOperation("更新用户")
    @PostMapping("/updateUser")
    public ResponseEntity updateUser(@RequestBody User user){
        if(user==null){
            return ResponseEntity.errorMsg("用户信息不能为空!");
        }

        return  ResponseEntity.okMap(userService.update(user));
    }

    /**
     * 删除
     *
     * @param id 用户
     * @return {@link ResponseEntity}
     */
    @ApiOperation("删除用户")
    @PostMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable String id){
        if(id==null||"".equals(id)){
            return ResponseEntity.errorMsg("用户id不能为空!");
        }

        return  ResponseEntity.okMap(userService.deleteById(id));
    }


}