package com.wang.security.app;

import com.wang.security.core.properties.ClientProperties;
import com.wang.security.core.properties.SecurityProperties;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author wangxiamei
 * @Description: 认证服务器的配置
 * @Date 2018/12/9 22:32
 */
@Configuration
@EnableAuthorizationServer
public class WangAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private TokenStore tokenStore;

    @Autowired(required = false)
    private JwtAccessTokenConverter converter;
    @Autowired
    private TokenEnhancer tokenEnhancer;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        super.configure(security);
    }

    //可认证的第三方应用的配置信息
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        ClientProperties[] oauth2 = securityProperties.getOauth2().getClients();
        if (ArrayUtils.isEmpty(oauth2)) {
            throw new InternalAuthenticationServiceException("client content can't empty");
        }
        InMemoryClientDetailsServiceBuilder serviceBuilder = clients.inMemory();
        for (ClientProperties clientProperties : oauth2) {

            String scopes = clientProperties.getScopes();
            String[] scopesArray = null;
            if (StringUtils.isNotBlank(scopes)) {
                scopesArray = StringUtils.split(scopes, ",");
            }
            String grandType = clientProperties.getGrandType();
            String[] grandTypeArray = null;
            if (StringUtils.isNotBlank(grandType)) {
                grandTypeArray = StringUtils.split(grandType, ",");
            }
            serviceBuilder.withClient(clientProperties.getClientId())
                    .autoApprove(true)
                    .secret(clientProperties.getClientSecret())
                    .accessTokenValiditySeconds(clientProperties.getAccesstokenInvalidSeconds())
                    .authorizedGrantTypes(grandTypeArray)
                    .scopes(scopesArray);
        }


        //        clients.inMemory()
//                .withClient("wang")
//                .secret("wang123456")
//                .accessTokenValiditySeconds(7200)
//                .authorizedGrantTypes("authorization_code","password","implicit","client_credentials","refresh_token")
//                .scopes("all","read","write")
//                .autoApprove(true)
//                .and()
//                .withClient("xia")
//                .secret("xia123456")
//                .autoApprove(true)
//                .scopes("all")
//                .authorizedGrantTypes("password")
//                .accessTokenValiditySeconds(1800);
//

    }

    //认证端点的配置
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore)
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService);
        if (converter != null && tokenEnhancer != null) {
            TokenEnhancerChain chain = new TokenEnhancerChain();
            List<TokenEnhancer> tokenEnhancers = new ArrayList<>();
            tokenEnhancers.add(tokenEnhancer);
            tokenEnhancers.add(converter);
            chain.setTokenEnhancers(tokenEnhancers);
            endpoints.tokenEnhancer(chain);
            //endpoints.accessTokenConverter(converter);
        }
    }


}
