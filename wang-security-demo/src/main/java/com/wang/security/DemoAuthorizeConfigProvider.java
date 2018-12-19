package com.wang.security;

import com.wang.security.core.authorize.AuthorizeConfigProvider;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

/**
 * @Author wangxiamei
 * @Description:
 * @Date 2018/12/19 15:49
 */
@Component
@Order(Integer.MAX_VALUE)
public class DemoAuthorizeConfigProvider implements AuthorizeConfigProvider {
    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
//        config.antMatchers(HttpMethod.GET,"/hello").hasRole("ADMIN")
//                .antMatchers(HttpMethod.GET,"/user/*").hasAnyAuthority("QUERY","find")
//                .antMatchers("/me").access("hasRole('master') and hasIpAddress('127.0.0.1')");

//        config.anyRequest().access("@rbacService.hasPermission(request,authentication)");
    }
}
