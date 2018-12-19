package com.wang.security.core.support;

/**
 * @Author wangxiamei
 * @Description:
 * @Date 2018/11/25 20:47
 */
public class SimpleResponse {
    public SimpleResponse(Object content) {
        this.content = content;
    }

    private Object content;

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
