package com.hls.pojo;

import java.util.Date;
import java.io.Serializable;
import lombok.Data;
import javax.persistence.*;

/**
 * (Dict)实体类
 *
 * @author liangyehao
 * @since 2020-03-29 16:55:10
 */
@Table(name = "dict")
@Data
public class Dict implements Serializable {
    private static final long serialVersionUID = -71766799846177838L;
    /**
    * 主键
    */
    @Id
    @Column(name = "id")
    private Integer id;
    /**
    * 代码
    */
    @Column(name = "code")
    private String code;
    /**
    * 中文
    */
    @Column(name = "value")
    private String value;
    /**
    * 类型
    */
    @Column(name = "kind")
    private String kind;
    /**
    * 创建者
    */
    @Column(name = "creator")
    private String creator;
    /**
    * 创建时间
    */
    @Column(name = "create_time")
    private Date createTime;


}