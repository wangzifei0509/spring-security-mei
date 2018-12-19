package com.wang.security.app;

import com.wang.security.app.openid.OpenidSecurityConfig;
import com.wang.security.core.validate.code.config.SecurityConstants;
import com.wang.security.core.validate.code.config.ValidateCodeSecurityConfig;
import com.wang.security.core.properties.SecurityProperties;
import com.wang.security.core.validate.code.sms.SmsSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * @Author wangxiamei
 * @Description: 资源服务器的配置
 * @Date 2018/12/9 23:15
 */
@Configuration
@EnableResourceServer
public class WangResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    private AuthenticationSuccessHandler sucessHandler;
    @Autowired
    private AuthenticationFailureHandler failureHandler;
    @Autowired
    private SmsSecurityConfig smsSecurityConfig;
    @Autowired
    private SpringSocialConfigurer springSocialConfigurer;
    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    @Autowired
    private OpenidSecurityConfig openidSecurityConfig;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage(SecurityConstants.DEFAULT_NO_AUTHEN_URL)
                .loginProcessingUrl(SecurityConstants.DEFAULT_IMAGE_URL)
                .successHandler(sucessHandler)
                .failureHandler(failureHandler)
                .and()
                .apply(validateCodeSecurityConfig)
                .and()
                .apply(smsSecurityConfig)
                .and()
                .apply(openidSecurityConfig)
                .and()
                .apply(springSocialConfigurer)
                .and()
                .authorizeRequests()
                .antMatchers(SecurityConstants.DEFAULT_NO_AUTHEN_URL,
                        securityProperties.getBrowser().getLoginPage(),
                        SecurityConstants.DEFAULT_SMS_URL,
                        SecurityConstants.VALIDATE_CODE_PREFIX + "/*",
                        securityProperties.getBrowser().getSignOutUrl()
                ).permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf()
                .disable();
    }
}
