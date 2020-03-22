package com.hls.controller;

import com.hls.pojo.Book;
import com.hls.pojo.dto.BarcodeDTO;
import com.hls.pojo.dto.BookInfoDTO;
import com.hls.pojo.dto.WxLoginDTO;
import com.hls.pojo.vo.ResponseEntity;
import com.hls.utils.HtmlParseUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author liangyehao
 * @version 1.0
 * @date 2020/3/13 19:50
 * @content
 */
@RestController
@RequestMapping("/main")
public class MainController {

    @Value("${isbn.url}")
    private String ISBN_URL;

    @Value("${openidUrl}")
    private String openidUrl;

    @Value("${appid}")
    private String appid;
    @Value("${secret}")
    private String secret;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 扫描条形码
     *
     * @param barcodeDTO 条形码
     */
    @PostMapping("/scanBarcode")
    public ResponseEntity scanBarcode(@RequestBody BarcodeDTO barcodeDTO) throws IOException {
        //9787547247327
        Book book = new Book();
        BookInfoDTO bookInfo = restTemplate.getForObject(ISBN_URL + barcodeDTO.getResult(), BookInfoDTO.class);
        if (bookInfo != null) {
            Map<String, String> bookMap = HtmlParseUtil.handleHtml(bookInfo.getUrl());
            System.out.println(bookMap);

            book.setCoverUrl(bookInfo.getCover_url());
            book.setDoubanUrl(bookInfo.getUrl());
            book.setAuthor(bookMap.get("作者"));
            book.setTitle(bookMap.get("书名"));
            book.setPub(bookMap.get("出版社"));
            book.setFraming(bookMap.get("装帧"));
            book.setSeries(bookMap.get("丛书"));
            book.setIsbn(bookMap.get("ISBN"));
            book.setTranslator(bookMap.get("译者"));

            if (StringUtils.isNotBlank(bookMap.get("定价"))) {
                book.setPrice(Double.parseDouble(bookMap.get("定价")));
            }
            if (StringUtils.isNotBlank(bookMap.get("出版年"))) {
                String publication = bookMap.get("出版年");
                String[] split = publication.split("-");
                int year = Integer.parseInt(split[0]);
                int month = Integer.parseInt(split[1]);
                LocalDateTime of = LocalDateTime.of(year, month, 1, 0, 0);
                book.setPublication(of);
            }
            if (StringUtils.isNotBlank(bookMap.get("页数"))) {
                book.setPages(Integer.parseInt(bookMap.get("页数")));
            }
        }

        return ResponseEntity.okMap(book);
    }

    @GetMapping("/test")
    public ResponseEntity test() {
        System.out.println("测试前后端连接");
        return ResponseEntity.ok();
    }

    @PostMapping("/getOpenid")
    public String getOpenid(@RequestBody WxLoginDTO wxLoginDTO) {
        System.out.println(wxLoginDTO);
        return restTemplate.getForObject(String.format(openidUrl, appid, secret, wxLoginDTO.getCode()), String.class);
    }


}
