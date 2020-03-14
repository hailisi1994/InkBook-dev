package com.hls.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;

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


}