package com.wang.security.app.openid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.stereotype.Component;

/**
 * @Author wangxiamei
 * @Description:  openid配置
 * @Date 2018/12/17 17:32
 */
@Component
public class OpenidSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain,HttpSecurity> {

    @Autowired
    private AuthenticationSuccessHandler sucessHandler;
    @Autowired
    private AuthenticationFailureHandler failureHandler;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private UsersConnectionRepository usersConnectionRepository;

    @Override
    public void configure(HttpSecurity http) throws Exception {
       OpenidAuthenticaitonFilter filter = new OpenidAuthenticaitonFilter();
       filter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
       filter.setAuthenticationSuccessHandler(sucessHandler);
       filter.setAuthenticationFailureHandler(failureHandler);

       OpenidAuthenticationProvider provider = new OpenidAuthenticationProvider();
       provider.setUserDetailsService(userDetailsService);
       provider.setUsersConnectionRepository(usersConnectionRepository);

       http.authenticationProvider(provider)
               .addFilterAfter(filter,UsernamePasswordAuthenticationFilter.class);

    }
}
