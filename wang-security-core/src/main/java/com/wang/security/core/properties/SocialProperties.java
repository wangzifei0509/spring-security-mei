package com.wang.security.core.properties;

/**
 * @Author wangxiamei
 * @Description: 社交登录相关的配置
 * @Date 2018/12/4 15:23
 */
public class SocialProperties {

    private String filterProcessesUrl = "/auth";

    private WeixinProperties weixin = new WeixinProperties();

    public String getFilterProcessesUrl() {
        return filterProcessesUrl;
    }

    public void setFilterProcessesUrl(String filterProcessesUrl) {
        this.filterProcessesUrl = filterProcessesUrl;
    }

    public WeixinProperties getWeixin() {
        return weixin;
    }

    public void setWeixin(WeixinProperties weixin) {
        this.weixin = weixin;
    }

    @Override
    public String toString() {
        return "SocialProperties{" +
                "filterProcessesUrl='" + filterProcessesUrl + '\'' +
                ", weixin=" + weixin +
                '}';
    }
}
