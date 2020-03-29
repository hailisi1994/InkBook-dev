package com.hls.pojo;

import java.util.Date;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * (Borrow)实体类
 *
 * @author liangyehao
 * @since 2020-03-15 13:22:49
 */
@Table(name = "borrow")
@Data
@Accessors(chain = true)
public class Borrow implements Serializable {
    private static final long serialVersionUID = 959556185142287769L;
    /**
    * 借书id
    */
    @Id
    @Column(name = "id")
    private String id;
    /**
    * 学生编号
    */
    @Column(name = "user_id")
    private String userId;
    /**
    * 书籍编号
    */
    @Column(name = "book_id")
    private String bookId;
    /**
    * isbn编号
    */
    @Column(name = "isbn")
    private String isbn;
    /**
    * 借书时间
    */
    @Column(name = "borrow_time")
    private Date borrowTime;
    /**
    * 归还时间
    */
    @Column(name = "return_time")
    private Date returnTime;
    /**
    * 是否归还
    */
    @Column(name = "if_return")
    private Integer ifReturn;

    /**
     * 还书期限
     */
    @Column(name = "deadline")
    private Date deadline;


}