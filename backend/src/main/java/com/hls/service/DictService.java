package com.hls.service;

/**
 * @author liangyehao
 * @version 1.0
 * @date 2020/3/29 16:59
 * @content 字典service
 */
public interface DictService {

    /**
     * 代码装换成中文
     *
     * @param code 代码
     * @param kind 类
     * @return {@link String}
     */
    String codeToValue(String code,String kind);
}
