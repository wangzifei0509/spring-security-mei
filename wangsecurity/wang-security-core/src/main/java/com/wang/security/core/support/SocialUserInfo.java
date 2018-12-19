package com.wang.security.core.support;

/**
 * @Author wangxiamei
 * @Description:
 * @Date 2018/12/17 20:10
 */
public class SocialUserInfo {

    private String providerId;
    private String providerUserid;
    private String nickName;
    private String headImg;

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getProviderUserid() {
        return providerUserid;
    }

    public void setProviderUserid(String providerUserid) {
        this.providerUserid = providerUserid;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }
}
