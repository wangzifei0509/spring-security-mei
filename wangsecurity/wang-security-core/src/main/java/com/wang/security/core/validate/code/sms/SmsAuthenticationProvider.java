package com.wang.security.core.validate.code.sms;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @Author wangxiamei
 * @Description:
 * @Date 2018/11/29 17:43
 */
public class SmsAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        SmsAuthenticationToken token = (SmsAuthenticationToken) authentication;
        //获取用户信息
        UserDetails user = userDetailsService.loadUserByUsername((String) token.getPrincipal());
        if (user == null) {
            throw new InternalAuthenticationServiceException("找不到相应的用户");
        }
        SmsAuthenticationToken newToken = new SmsAuthenticationToken(user.getUsername(), user.getAuthorities());
        newToken.setDetails(token.getDetails());
        return newToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return SmsAuthenticationToken.class.isAssignableFrom(authentication);
    }

    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
}
