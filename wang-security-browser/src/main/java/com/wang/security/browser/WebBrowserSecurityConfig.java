package com.wang.security.browser;

import com.wang.security.core.authorize.AuthorizeConfigManager;
import com.wang.security.core.validate.code.config.LoginSecurityConfig;
import com.wang.security.core.validate.code.config.ValidateCodeSecurityConfig;
import com.wang.security.core.properties.SecurityProperties;
import com.wang.security.core.validate.code.sms.SmsSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * @Author wangxiamei
 * @Description:
 * @Date 2018/11/25 17:42
 */
@Configuration
public class WebBrowserSecurityConfig extends LoginSecurityConfig {

    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private SmsSecurityConfig smsSecurityConfig;
    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;
    @Autowired
    private SpringSocialConfigurer springSocialConfigurer;
    @Autowired
    private InvalidSessionStrategy invalidSessionStrategy;
    @Autowired
    private SessionInformationExpiredStrategy sessionInformationExpiredStrategy;
    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;
    @Autowired
    private AuthorizeConfigManager authorizeConfigManagermanager;

    @Bean
    public JdbcTokenRepositoryImpl jdbcTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
//        tokenRepository.setCreateTableOnStartup(true);
        tokenRepository.setDataSource(dataSource);
        return tokenRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        applyPasswordAuthenticationConfig(http);
        http
                .apply(validateCodeSecurityConfig)
                .and()
                .apply(smsSecurityConfig)
                .and()
                .apply(springSocialConfigurer)
                .and()
                //.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
//                .formLogin()
//                .loginPage(SecurityConstants.DEFAULT_NO_AUTHEN_URL)
//                .loginProcessingUrl(SecurityConstants.DEFAULT_IMAGE_URL)
//                .successHandler(sucessHandle)
//                .failureHandler(failureHandler)
//                .and()
                .rememberMe()
                .tokenRepository(jdbcTokenRepository())
                .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
                .userDetailsService(userDetailsService)
                .and()
                .logout()
                //.logoutUrl("/signOut")
                .logoutSuccessUrl("/wang-signin.html")
                //.logoutSuccessHandler(logoutSuccessHandler)
                .deleteCookies("JSESSION")

//                .and()
//                .sessionManagement()
//                 //.invalidSessionUrl("/session/invalidate")//session失效后的处理url,与处理策略互斥
//                .invalidSessionStrategy(invalidSessionStrategy) //session失效后的处理策略
//                .maximumSessions(securityProperties.getBrowser().getSession().getMaximum())// 限制登录的最大人数
//                .maxSessionsPreventsLogin(securityProperties.getBrowser().getSession().getMaxSessionsPreventsLogin()) //超过最大登录人数之后是否不允许登录，true不允许登录，false挤掉之前的。
//                .expiredSessionStrategy(sessionInformationExpiredStrategy) // 因为多人登录超过最大人数之后的session失效处理策略
//                .and()
                .and()
                .csrf()
                .disable();
        authorizeConfigManagermanager.config(http.authorizeRequests());
    }
}
