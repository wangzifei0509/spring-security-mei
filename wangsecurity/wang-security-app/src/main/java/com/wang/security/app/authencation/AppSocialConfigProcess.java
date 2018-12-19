package com.wang.security.app.authencation;

import com.wang.security.core.weixin.process.SocialConfigProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * @Author wangxiamei
 * @Description:
 * @Date 2018/12/17 15:41
 */
@Component
public class AppSocialConfigProcess implements SocialConfigProcess  {

    @Autowired
    private AuthenticationSuccessHandler successHandler;
    @Override
    public void process(SocialAuthenticationFilter filter) {
        filter.setAuthenticationSuccessHandler(successHandler);
    }
}
