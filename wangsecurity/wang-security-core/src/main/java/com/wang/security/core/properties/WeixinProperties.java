package com.wang.security.core.properties;

import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * @Author wangxiamei
 * @Description: weixin 相关配置
 * @Date 2018/12/4 15:22
 */
public class WeixinProperties extends SocialProperties {

    private String providerId = "weixin";

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }
}
