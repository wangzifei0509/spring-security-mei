package com.wang.security.app.token;

import com.wang.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * @Author wangxiamei
 * @Description: 处理token的存储问题
 * @Date 2018/12/18 16:24
 */
@Configuration
public class TokenStoreConfig {
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Bean
    @ConditionalOnProperty(prefix = "wang.security.oauth2",name="tokenStore",havingValue="redis",matchIfMissing = false)
    public TokenStore tokenStore(){
        return new RedisTokenStore(redisConnectionFactory);
    }

    @Configuration
    @ConditionalOnProperty(prefix = "wang.security.oauth2",name = "tokenStore",havingValue="jwt",matchIfMissing = true)
    public static class JwtTokenStoreConfig{
        @Autowired
        private SecurityProperties securityProperties;

        @Bean
        public TokenStore tokenStore(){
            return new JwtTokenStore(jwtAccessTokenConverter());
        }
        @Bean
        public JwtAccessTokenConverter jwtAccessTokenConverter(){
            JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
            converter.setSigningKey(securityProperties.getOauth2().getJwtSignKey());
            return converter;
        }
        @Bean
        @ConditionalOnMissingBean(type = "tokenEnhancer")
        public TokenEnhancer tokenEnhancer(){
            return new DefaultTokenEnhancer();
        }

    }
}
