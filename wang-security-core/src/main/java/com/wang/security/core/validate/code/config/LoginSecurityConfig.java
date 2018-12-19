package com.wang.security.core.validate.code.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * @Author wangxiamei
 * @Description:
 * @Date 2018/11/30 11:12
 */
public class LoginSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationSuccessHandler sucessHandle;
    @Autowired
    private AuthenticationFailureHandler failureHandler;

    protected void applyPasswordAuthenticationConfig(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage(SecurityConstants.DEFAULT_NO_AUTHEN_BROWSER_URL)
                .loginProcessingUrl(SecurityConstants.DEFAULT_IMAGE_URL)
                .successHandler(sucessHandle)
                .failureHandler(failureHandler);

    }
}
