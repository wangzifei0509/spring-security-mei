package com.wang.security.browser.session;

import org.springframework.security.web.session.InvalidSessionStrategy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author wangxiamei
 * @Description: session失效后的处理策略
 * @Date 2018/12/9 13:53
 */
public class WangInvalidSessionStrategy extends AbstractSessionStrategy implements InvalidSessionStrategy {


    public WangInvalidSessionStrategy(String redirectURL) {
        super(redirectURL);
    }

    @Override
    public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        onSessionInvalid(request,response);
    }
}
