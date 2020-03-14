package com.hls.pojo.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author liangyehao
 * @version 1.0
 * @date 2020/3/13 22:58
 * @content 返回前端实体
 */
@Data
@Accessors(chain = true)
public class ResponseEntity {

    private int status;

    private String msg;

    private Object data;

    public static ResponseEntity ok(){
        return new ResponseEntity().setStatus(200);
    }

    public static ResponseEntity okMap(Object data){
        return new ResponseEntity().setStatus(200).setData(data);
    }

    public static ResponseEntity error(Object data){
        return new ResponseEntity().setStatus(500);
    }

    public static ResponseEntity errorMap(Object data){
        return new ResponseEntity().setStatus(500).setData(data);
    }
}
