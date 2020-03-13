package com.hls.pojo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @author liangyehao
 * @version 1.0
 * @date 2020/3/13 22:28
 * @content 图书信息
 */
@Data
public class BookInfoDTO {

    /**
     * create_time : 2020-03-13 21:24:16
     * isbn : 9787561345948
     * title : 不抱怨的世界
     * abstract : [美] 威尔·鲍温 / 陈敬旻 / 陕西师范大学出版社 / 2009-4 / 24.80
     * book_intro : 抱怨是最消耗能量的无益举动。
     * author_intro : 威尔·鲍温（Will Bowen），美国最伟大、受尊崇的心灵导师之一。
     * catalog : （前言）紫手环的力量
     * original_texts : ["欣赏能激励人们表现优越，以获得更多上市；批评则使人损耗，当我们贬低别人时，其实也是在默许此人往后依然故我。"]
     * labels : ["励志","不抱怨的世界","修身养性，完善自我","心理","心理学","成长","不抱怨","心灵"]
     * cover_url : https://img9.doubanio.com/view/subject/l/public/s3694090.jpg
     * url : https://book.douban.com/subject/3622904/
     * source : redis
     */

    private String create_time;
    private String isbn;
    private String title;
    @JsonProperty("abstract")
    private String summary;
    private String book_intro;
    private String author_intro;
    private Object catalog;
    private String cover_url;
    private String url;
    private String source;
    private List<String> original_texts;
    private List<String> labels;

    private Rating rating;
    private List<Comments> comments;

}
