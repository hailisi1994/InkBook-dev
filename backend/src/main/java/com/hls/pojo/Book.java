package com.hls.pojo;

import java.time.LocalDateTime;
import java.util.Date;
import java.io.Serializable;
import lombok.Data;
import javax.persistence.*;

/**
 * (Book)实体类
 *
 * @author liangyehao
 * @since 2020-03-21 22:22:07
 */
@Table(name = "book")
@Data
public class Book implements Serializable {
    private static final long serialVersionUID = -24008301568114054L;
    /**
    * 唯一书籍序号
    */
    @Id
    @Column(name = "id")
    private String id;
    /**
    * 国际标准书号
    */
    @Column(name = "isbn")
    private String isbn;
    /**
    * 书籍名称
    */
    @Column(name = "title")
    private String title;
    /**
    * 书籍作者
    */
    @Column(name = "author")
    private String author;
    /**
    * 书籍出版社
    */
    @Column(name = "pub")
    private String pub;
    /**
    * 书籍是否在架上
    */
    @Column(name = "if_on")
    private Integer ifOn;
    /**
    * 书籍分类
    */
    @Column(name = "sort")
    private String sort;
    /**
    * 创建时间
    */
    @Column(name = "create_time")
    private Date createTime;
    /**
    * 修改时间
    */
    @Column(name = "update_time")
    private Date updateTime;
    /**
    * 豆瓣url
    */
    @Column(name = "douban_url")
    private String doubanUrl;
    /**
    * 封面url
    */
    @Column(name = "cover_url")
    private String coverUrl;
    /**
    * 册数
    */
    @Column(name = "measure")
    private String measure;
    /**
    * 装帧
    */
    @Column(name = "framing")
    private String framing;
    /**
    * 系列
    */
    @Column(name = "series")
    private String series;
    /**
    * 翻译者
    */
    @Column(name = "translator")
    private String translator;
    /**
    * 价格
    */
    @Column(name = "price")
    private Double price;
    /**
    * 页数
    */
    @Column(name = "pages")
    private Integer pages;

    /**
    * 出版时间
    */
    @Column(name = "publication")
    private LocalDateTime publication;

    /**
     * 位置
     */
    @Column(name = "location")
    private String location;

}