package com.wang.security.core.properties;

import com.wang.security.core.validate.code.config.SecurityConstants;

/**
 * @Author wangxiamei
 * @Description:
 * @Date 2018/11/28 19:20
 */
public class SmsProperties {

    private Integer length = 6;
    private Integer expire = 60;

    private String url = SecurityConstants.DEFAULT_SMS_URL;


    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getExpire() {
        return expire;
    }

    public void setExpire(Integer expire) {
        this.expire = expire;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
