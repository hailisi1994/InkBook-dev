package com.hls.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

/**
 * (User)实体类
 *
 * @author liangyehao
 * @since 2020-03-14 17:08:45
 */
@Table(name = "user")
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 130215951112923096L;
    /**
    * 主键
    */
    @Column(name = "id")
    private String id;
    /**
    * 微信openid
    */
    @Column(name = "openid")
    private String openid;
    /**
    * 姓名
    */
    @Column(name = "name")
    private String name;
    /**
    * 昵称
    */
    @Column(name = "nick_name")
    private String nickName;
    /**
    * 年龄
    */
    @Column(name = "age")
    private Integer age;
    /**
    * 专业
    */
    @Column(name = "pro")
    private String pro;
    /**
    * 年级
    */
    @Column(name = "grade")
    private String grade;
    /**
    * 性别
    */
    @Column(name = "gender")
    private String gender;
    /**
    * 联系方式
    */
    @Column(name = "phone")
    private String phone;
    /**
    * 诚信级
    */
    @Column(name = "integrity")
    private Integer integrity;
    /**
    * 用户头像
    */
    @Column(name = "avatar_url")
    private String avatarUrl;

    /**
     * 密码
     */
    @Column(name = "password")
    private String password;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 角色
     */
    @Column(name = "role")
    private Integer role;

    /**
     * 确认密码
     */
    @Transient
    private String confirmPwd;


}