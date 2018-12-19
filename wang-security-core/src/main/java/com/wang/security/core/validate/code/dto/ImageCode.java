package com.wang.security.core.validate.code.dto;

import java.awt.image.BufferedImage;

/**
 * @Author wangxiamei
 * @Description: 图片验证码
 * @Date 2018/11/28 15:57
 */
public class ImageCode extends ValidateCode {
    private BufferedImage image;

    public ImageCode(String code, BufferedImage image, int expireSeconds) {
        super(code, expireSeconds);
        this.image = image;

    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
