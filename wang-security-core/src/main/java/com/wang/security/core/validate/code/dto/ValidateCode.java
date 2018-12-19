package com.wang.security.core.validate.code.dto;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author wangxiamei
 * @Description: 校验码
 * @Date 2018/11/28 15:57
 */
public class ValidateCode implements Serializable {

    private static final long serialVersionUID = 1L;
    private String code;
    private LocalDateTime expire;
    public ValidateCode(String code, LocalDateTime expire) {
        this.code = code;
        this.expire = expire;
    }


    public ValidateCode(String code, int expireSeconds) {
        this.code = code;
        this.expire = LocalDateTime.now().plusSeconds(expireSeconds);
    }

    public boolean isExpired() {
        return expire.isBefore(LocalDateTime.now());
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpire() {
        return expire;
    }

    public void setExpire(LocalDateTime expire) {
        this.expire = expire;
    }


}
