package com.wang.security.browser.logout;

import com.wang.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
 * @Author wangxiamei
 * @Description:
 * @Date 2018/12/9 20:48
 */
@Configuration
public class LogoutBeanConfig {

    @Autowired
    private SecurityProperties securityProperties;

    @Bean
    @ConditionalOnClass(LogoutSuccessHandler.class)
    public LogoutSuccessHandler logoutSuccessHandler(){
        return new WangLogoutSuccessHandler(securityProperties.getBrowser().getSignOutUrl());
    }
}
