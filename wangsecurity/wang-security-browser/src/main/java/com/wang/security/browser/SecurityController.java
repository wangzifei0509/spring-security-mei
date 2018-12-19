package com.wang.security.browser;

import com.wang.security.core.support.SimpleResponse;
import com.wang.security.core.support.SocialUserInfo;
import com.wang.security.core.properties.SecurityProperties;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author wangxiamei
 * @Description:
 * @Date 2018/11/25 20:38
 */
@RestController
public class SecurityController {
    @Autowired
    private ProviderSignInUtils utils;

    private Logger logger = LoggerFactory.getLogger(SecurityController.class);
    
    private RequestCache requestCache = new HttpSessionRequestCache();

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private SecurityProperties securityProperties;

    @RequestMapping("/security/require")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public SimpleResponse require(HttpServletRequest request,HttpServletResponse response) throws IOException {
        SavedRequest savedRequest = requestCache.getRequest(request, response);

        if(savedRequest != null){
            String redirectUrl = savedRequest.getRedirectUrl();
            logger.info("请求登录的url是：{}",redirectUrl);
            if(StringUtils.endsWithIgnoreCase(redirectUrl,".html")){
                redirectStrategy.sendRedirect(request,response,securityProperties.getBrowser().getLoginPage());
            }
        }
        return new SimpleResponse("访问的服务需要 身份认证");
    }

//    @RequestMapping("/session/invalidate")
//    @ResponseStatus(HttpStatus.UNAUTHORIZED)
//    public SimpleResponse sessionInvalidate(HttpServletRequest request,HttpServletResponse response){
//        response.setContentType("application/json;charset=utf-8");
//        return new SimpleResponse("session失效");
//    }

    @GetMapping("/social/user")
    public SocialUserInfo socialUser(HttpServletRequest request){
        SocialUserInfo info = new SocialUserInfo();
        Connection<?> connectionFromSession = utils.getConnectionFromSession(new ServletWebRequest(request));
        info.setProviderId(connectionFromSession.getKey().getProviderId());
        info.setProviderUserid(connectionFromSession.getKey().getProviderUserId());
        info.setProviderUserid(connectionFromSession.getDisplayName());
        info.setHeadImg(connectionFromSession.getImageUrl());
        return info;
    }

}
