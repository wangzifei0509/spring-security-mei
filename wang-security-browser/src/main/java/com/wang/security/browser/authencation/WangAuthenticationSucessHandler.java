package com.wang.security.browser.authencation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wang.security.core.validate.code.config.LoginSecurityType;
import com.wang.security.core.properties.SecurityProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author wangxiamei
 * @Description: 自定义成功处理器
 * @Date 2018/11/25 22:42
 */
@Component("wangAuthenticationSucessHandler")
public class WangAuthenticationSucessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private Logger logger = LoggerFactory.getLogger(WangAuthenticationSucessHandler.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication
            authentication) throws IOException, ServletException {

        if(LoginSecurityType.JSON.equals(securityProperties.getBrowser().getType())) {

            logger.info("请求登录成功");
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write("sucess");
        }else {
           response.sendRedirect(securityProperties.getBrowser().getSignInUrl());
        }
    }
}
