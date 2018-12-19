package com.wang.security.core.properties;

/**
 * @Author wangxiamei
 * @Description:
 * @Date 2018/11/28 19:20
 */
public class CodeProperties {
    private ImageProperties image = new ImageProperties();
    private SmsProperties sms = new SmsProperties();

    public ImageProperties getImage() {
        return image;
    }

    public void setImage(ImageProperties image) {
        this.image = image;
    }

    public SmsProperties getSms() {
        return sms;
    }

    public void setSms(SmsProperties sms) {
        this.sms = sms;
    }
}
