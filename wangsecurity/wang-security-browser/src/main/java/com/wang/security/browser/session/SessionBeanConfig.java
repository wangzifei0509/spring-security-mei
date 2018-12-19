package com.wang.security.browser.session;

import com.wang.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

/**
 * @Author wangxiamei
 * @Description:
 * @Date 2018/12/9 14:40
 */
@Configuration
public class SessionBeanConfig {

    @Autowired
    private SecurityProperties securityProperties;

    @Bean
    @ConditionalOnClass(InvalidSessionStrategy.class)
    public InvalidSessionStrategy invalidSessionStrategy(){
        return new WangInvalidSessionStrategy(securityProperties.getBrowser().getSession().getInvalidSessionUrl());
    }

    @Bean
    @ConditionalOnClass(SessionInformationExpiredStrategy.class)
    public SessionInformationExpiredStrategy sessionInformationExpiredStrategy(){
        return new WangSessionInformationExpiredStrategy(securityProperties.getBrowser().getSession().getInvalidSessionUrl());
    }
}
