package com.wang.security.app.token;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author wangxiamei
 * @Description:
 * @Date 2018/12/18 19:56
 */
public class DefaultTokenEnhancer implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Map<String,Object> addInformation = new HashMap<>();
        addInformation.put("smart","wang");
        ((DefaultOAuth2AccessToken)accessToken).setAdditionalInformation(addInformation);
        return accessToken;
    }
}
