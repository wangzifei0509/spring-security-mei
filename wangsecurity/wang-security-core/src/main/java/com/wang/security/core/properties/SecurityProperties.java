package com.wang.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author wangxiamei
 * @Description:
 * @Date 2018/11/25 20:51
 */
@ConfigurationProperties(prefix = "wang.security")
public class SecurityProperties {
    private BrowserProperties browser = new BrowserProperties();
    private CodeProperties code = new CodeProperties();
    private SocialProperties social = new SocialProperties();
    private Oauth2Properties oauth2 = new Oauth2Properties();

    public BrowserProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }

    public CodeProperties getCode() {
        return code;
    }

    public void setCode(CodeProperties code) {
        this.code = code;
    }

    public SocialProperties getSocial() {
        return social;
    }

    public void setSocial(SocialProperties social) {
        this.social = social;
    }

    public Oauth2Properties getOauth2() {
        return oauth2;
    }

    public void setOauth2(Oauth2Properties oauth2) {
        this.oauth2 = oauth2;
    }
}
