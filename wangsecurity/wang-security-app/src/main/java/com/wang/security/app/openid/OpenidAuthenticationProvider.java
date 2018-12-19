package com.wang.security.app.openid;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.social.connect.UsersConnectionRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author wangxiamei
 * @Description: 根据openid校验通过授权
 * @Date 2018/12/17 16:51
 */
public class OpenidAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;

    private UsersConnectionRepository usersConnectionRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        OpenidAuthenticationToken token = (OpenidAuthenticationToken) authentication;

        Set<String> providerUserIds = new HashSet<>();
        providerUserIds.add(token.getOpenid());
        //获取用户信息
        Set<String> userIds = usersConnectionRepository.findUserIdsConnectedTo(token.getProviderId(), providerUserIds);
        if (CollectionUtils.isEmpty(userIds) || userIds.size() != 1) {
            throw new InternalAuthenticationServiceException("can't find userid");
        }
        String userid = userIds.iterator().next();
        UserDetails userDetails = userDetailsService.loadUserByUsername(userid);

        if (userDetails == null) {
            throw new InternalAuthenticationServiceException("找不到相应的用户");
        }
        OpenidAuthenticationToken newToken = new OpenidAuthenticationToken(token.getOpenid(), token.getProviderId(), userDetails.getAuthorities());
        newToken.setDetails(token.getDetails());
        return newToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return OpenidAuthenticationToken.class.isAssignableFrom(authentication);
    }

    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public UsersConnectionRepository getUsersConnectionRepository() {
        return usersConnectionRepository;
    }

    public void setUsersConnectionRepository(UsersConnectionRepository usersConnectionRepository) {
        this.usersConnectionRepository = usersConnectionRepository;
    }
}
