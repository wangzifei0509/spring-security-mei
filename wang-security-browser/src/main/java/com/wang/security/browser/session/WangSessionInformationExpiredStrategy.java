package com.wang.security.browser.session;

import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * @Author wangxiamei
 * @Description: 登录超过限制人数之后的处理
 * @Date 2018/12/9 13:53
 */
public class WangSessionInformationExpiredStrategy extends AbstractSessionStrategy implements SessionInformationExpiredStrategy {

    public WangSessionInformationExpiredStrategy(String redirectURL) {
        super(redirectURL);
    }

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent eventØ) throws IOException, ServletException {
        onSessionInvalid(eventØ.getRequest(),eventØ.getResponse());
    }

    @Override
    protected boolean isConcurrent() {
        return true;
    }
}
