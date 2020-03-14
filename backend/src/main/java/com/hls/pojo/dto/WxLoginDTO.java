package com.hls.pojo.dto;

import lombok.Data;

/**
 * @author liangyehao
 * @version 1.0
 * @date 2020/3/14 13:02
 * @content
 */
@Data
public class WxLoginDTO {

    /**
     * code : res.code
     * appid : 你的小程序AppID
     * secret : 你的小程序secret
     */

    private String errMsg;
    private String code;
    private String appid;
    private String secret;

}
