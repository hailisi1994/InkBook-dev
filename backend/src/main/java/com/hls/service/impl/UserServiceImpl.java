package com.hls.service.impl;

import com.hls.pojo.User;
import com.hls.dao.UserDao;
import com.hls.service.UserService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * (User)表服务实现类
 *
 * @author liangyehao
 * @since 2020-03-14 17:08:48
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public User queryById(String id) {
        return this.userDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<User> queryAllByLimit(int offset, int limit) {
        return this.userDao.queryAllByLimit(offset, limit);
    }

    /**
     * 查询所有数据
     *
     * @return 对象列表
     */
    @Override
    public List<User> queryAll(User user) {
        return this.userDao.queryAll(user);
    }

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User insert(User user) {
        user.setId(UUID.randomUUID().toString());
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        user.setIntegrity(10);
        user.setRole(0);
        this.userDao.insert(user);
        return user;
    }

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User update(User user) {
        user.setUpdateTime(new Date());
        this.userDao.update(user);
        return this.queryById(user.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.userDao.deleteById(id) > 0;
    }


    /**
     * 检查
     *
     * @param phone    电话
     * @param password 密码
     * @return {@link User}
     */
    @Override
    public List<User> check(String phone, String password) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("phone",phone);
        criteria.andEqualTo("password",password);

        return userDao.selectByExample(example);
    }

    /**
     * 检查用户是否存在
     *
     * @param phone 电话
     * @return {@link Boolean}
     */
    @Override
    public Boolean checkExist(String phone) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("phone",phone);
        List<User> users = userDao.selectByExample(example);
        return users.size()>0;
    }

}