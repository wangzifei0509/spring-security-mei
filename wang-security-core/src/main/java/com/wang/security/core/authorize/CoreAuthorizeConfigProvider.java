package com.wang.security.core.authorize;

import com.wang.security.core.validate.code.config.SecurityConstants;
import com.wang.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

/**
 * @Author wangxiamei
 * @Description:
 * @Date 2018/12/19 15:43
 */
@Component
@Order(Integer.MIN_VALUE)
public class CoreAuthorizeConfigProvider implements AuthorizeConfigProvider{

    @Autowired
    private SecurityProperties securityProperties;
    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        config.antMatchers(SecurityConstants.DEFAULT_NO_AUTHEN_BROWSER_URL,
                securityProperties.getBrowser().getLoginPage(),
                SecurityConstants.DEFAULT_SMS_URL,
                SecurityConstants.VALIDATE_CODE_PREFIX + "/*",
                securityProperties.getBrowser().getSignOutUrl(),
                securityProperties.getBrowser().getSignUpUrl(),
                SecurityConstants.DEFAULT_REGIST_URL)
                .permitAll();
    }
}
