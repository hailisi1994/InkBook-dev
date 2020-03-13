package com.hls.pojo.dto;

import lombok.Data;

/**
 * @author liangyehao
 * @version 1.0
 * @date 2020/3/13 19:52
 * @content 条形码
 */
@Data
public class BarcodeDTO {


    /**
     * result : 9787561345948
     * charSet : ISO8859-1
     * errMsg : scanCode:ok
     * scanType : EAN_13
     * rawData : OTc4NzU2MTM0NTk0OA==
     */

    private String result;
    private String charSet;
    private String errMsg;
    private String scanType;
    private String rawData;

}
