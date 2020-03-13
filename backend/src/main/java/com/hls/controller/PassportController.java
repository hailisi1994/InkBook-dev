package com.hls.controller;

import com.alibaba.fastjson.JSON;
import com.hls.pojo.dto.BarcodeDTO;
import com.hls.pojo.dto.BookInfoDTO;
import com.hls.pojo.vo.ResponseEntity;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * @author liangyehao
 * @version 1.0
 * @date 2020/3/13 19:50
 * @content
 */
@RestController
@RequestMapping("/passport")
public class PassportController {

    @Value("${isbn.url}")
    private String ISBN_URL;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 扫描条形码
     *
     * @param barcodeDTO 条形码
     */
    @PostMapping("/scanBarcode")
    public ResponseEntity scanBarcode(@RequestBody BarcodeDTO barcodeDTO){
        BookInfoDTO bookInfo = restTemplate.getForObject(ISBN_URL + barcodeDTO.getResult(), BookInfoDTO.class);
        String s = JSON.toJSONString(bookInfo);
        System.out.println(barcodeDTO);
        System.out.println(s);
        return ResponseEntity.okMap(bookInfo);
    }

    @GetMapping("/test")
    public void test(){
        System.out.println("测试前后端连接");
    }

}
