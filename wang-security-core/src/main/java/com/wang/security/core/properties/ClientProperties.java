package com.wang.security.core.properties;

/**
 * @Author wangxiamei
 * @Description: 第三方服务的信息
 * @Date 2018/12/18 15:58
 */
public class ClientProperties {

    private String clientId;
    private String clientSecret;
    private Integer accesstokenInvalidSeconds = 1*60;
    private String scopes = "all";
    private String grandType = "password";

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public Integer getAccesstokenInvalidSeconds() {
        return accesstokenInvalidSeconds;
    }

    public void setAccesstokenInvalidSeconds(Integer accesstokenInvalidSeconds) {
        this.accesstokenInvalidSeconds = accesstokenInvalidSeconds;
    }

    public String getScopes() {
        return scopes;
    }

    public void setScopes(String scopes) {
        this.scopes = scopes;
    }

    public String getGrandType() {
        return grandType;
    }

    public void setGrandType(String grandType) {
        this.grandType = grandType;
    }
}
