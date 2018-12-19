package com.wang.security.app.authencation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wang.security.core.support.SimpleResponse;
import com.wang.security.core.validate.code.config.LoginSecurityType;
import com.wang.security.core.properties.SecurityProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author wangxiamei
 * @Description: 自定义登录失败处理器
 * @Date 2018/11/25 22:42
 */
@Component("wangAuthenticationFailureHandler")
public class WangAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    private Logger logger = LoggerFactory.getLogger(WangAuthenticationFailureHandler.class);

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception)
            throws IOException, ServletException {

        if(LoginSecurityType.JSON.equals(securityProperties.getBrowser().getType())){
            logger.info("请求登录处理失败,e",exception);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(objectMapper.writeValueAsString(new SimpleResponse(exception.getMessage())));
        } else {
            super.onAuthenticationFailure(request,response,exception);
        }


    }
}
