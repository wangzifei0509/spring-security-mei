package com.wang.security.core.properties;

import com.wang.security.core.validate.code.config.SecurityConstants;

/**
 * @Author wangxiamei
 * @Description: session相关配置
 * @Date 2018/12/9 15:08
 */
public class SessionProperties {
    private Integer  maximum =1;
    private Boolean maxSessionsPreventsLogin = true;
    private String invalidSessionUrl = SecurityConstants.DEFAULT_NO_SESSION_URL;

    public Integer getMaximum() {
        return maximum;
    }

    public void setMaximum(Integer maximum) {
        this.maximum = maximum;
    }

    public Boolean getMaxSessionsPreventsLogin() {
        return maxSessionsPreventsLogin;
    }

    public void setMaxSessionsPreventsLogin(Boolean maxSessionsPreventsLogin) {
        this.maxSessionsPreventsLogin = maxSessionsPreventsLogin;
    }

    public String getInvalidSessionUrl() {
        return invalidSessionUrl;
    }

    public void setInvalidSessionUrl(String invalidSessionUrl) {
        this.invalidSessionUrl = invalidSessionUrl;
    }
}
