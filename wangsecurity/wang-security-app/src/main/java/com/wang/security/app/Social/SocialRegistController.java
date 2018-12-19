package com.wang.security.app.Social;

import com.wang.security.core.support.SocialUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author wangxiamei
 * @Description: 处理社交登录注册的逻辑
 * @Date 2018/12/18 11:05
 */
@RestController
public class SocialRegistController {

    @Autowired
    private RedisProviderUtil redisProviderUtil;
    @Autowired
    private ProviderSignInUtils providerSignInUtils;

    @RequestMapping("/wangSignUp")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public SocialUserInfo  registPage(HttpServletRequest request) {
        Connection<?> connectionFromSession = providerSignInUtils.getConnectionFromSession(new ServletWebRequest(request));
        redisProviderUtil.setConnectionFromRedis(connectionFromSession);
        SocialUserInfo info = new SocialUserInfo();
        info.setProviderId(connectionFromSession.getKey().getProviderId());
        info.setProviderUserid(connectionFromSession.getKey().getProviderUserId());
        info.setProviderUserid(connectionFromSession.getDisplayName());
        info.setHeadImg(connectionFromSession.getImageUrl());
        return info;
    }

}
