package com.wang.security.browser.logout;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wang.security.core.support.SimpleResponse;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author wangxiamei
 * @Description: 退出成功的处理器
 * 如果配置了退成成功的url就重定向到url，没有就返回json
 * @Date 2018/12/9 20:43
 */
public class WangLogoutSuccessHandler implements LogoutSuccessHandler {

    private Logger logger = LoggerFactory.getLogger(WangLogoutSuccessHandler.class);

    private String signOutUrl;

    private ObjectMapper mapper = new ObjectMapper();

    public WangLogoutSuccessHandler(String signOutUrl) {
        this.signOutUrl = signOutUrl;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        logger.info(authentication.getPrincipal()+"退出登录成功！");
        if (StringUtils.isNotBlank(signOutUrl)) {
            //重定向到该页面
            response.sendRedirect(signOutUrl);
        } else {
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(mapper.writeValueAsString(new SimpleResponse("退出成功")));
        }

    }
}
