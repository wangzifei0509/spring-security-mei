package com.wang.security.core.properties;

import com.wang.security.core.validate.code.config.LoginSecurityType;

/**
 * @Author wangxiamei
 * @Description:
 * @Date 2018/11/25 20:52
 */
public class BrowserProperties {
    private String loginPage = "/wang-signin.html";

    private LoginSecurityType type = LoginSecurityType.JSON;

    private Integer rememberMeSeconds = 3600;

    private String signUpUrl = "/wang-regist.html";

    private SessionProperties session = new SessionProperties();

    private String signOutUrl = "/demo-signout.html";

    private String signInUrl = "/index.html";

    public String getSignUpUrl() {
        return signUpUrl;
    }

    public void setSignUpUrl(String signUpUrl) {
        this.signUpUrl = signUpUrl;
    }

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public LoginSecurityType getType() {
        return type;
    }

    public void setType(LoginSecurityType type) {
        this.type = type;
    }

    public Integer getRememberMeSeconds() {
        return rememberMeSeconds;
    }

    public void setRememberMeSeconds(Integer rememberMeSeconds) {
        this.rememberMeSeconds = rememberMeSeconds;
    }

    public SessionProperties getSession() {
        return session;
    }

    public void setSession(SessionProperties session) {
        this.session = session;
    }

    public String getSignOutUrl() {
        return signOutUrl;
    }

    public void setSignOutUrl(String signOutUrl) {
        this.signOutUrl = signOutUrl;
    }

    public String getSignInUrl() {
        return signInUrl;
    }

    public void setSignInUrl(String signInUrl) {
        this.signInUrl = signInUrl;
    }
}
