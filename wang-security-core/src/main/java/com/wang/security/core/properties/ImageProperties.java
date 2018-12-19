package com.wang.security.core.properties;

/**
 * @Author wangxiamei
 * @Description:
 * @Date 2018/11/28 19:20
 */
public class ImageProperties  extends  SmsProperties{
    public ImageProperties(){
        setLength(4);
    }

    private Integer width = 60;
    private Integer height = 20;

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

}
