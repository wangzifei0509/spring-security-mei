package com.wang.security.browser.session;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wang.security.core.support.SimpleResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author wangxiamei
 * @Description: 抽象的sessionStrategy处理
 * @Date 2018/12/9 14:08
 */
public class AbstractSessionStrategy {

    private Logger logger = LoggerFactory.getLogger(AbstractSessionStrategy.class);

    private String redirectURL;

    private RedirectStrategy strategy = new DefaultRedirectStrategy();

    private boolean newSession = true;

    private ObjectMapper mapper = new ObjectMapper();

    public AbstractSessionStrategy(String redirectURL) {
        Assert.isTrue(UrlUtils.isValidRedirectUrl(redirectURL),"url must start with '/' or with 'http'");
        this.redirectURL = redirectURL;
    }

    protected void onSessionInvalid(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestURI = request.getRequestURI();
//        if(requestURI.endsWith(".html")){
//            redirectURL +=".html";
//            //重定向
//            strategy.sendRedirect(request,response,redirectURL);
//        } else {
            //json
            String message = "session 失效";
            if(isConcurrent()){
                 message += "由于并发登录导致的";
            }
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(mapper.writeValueAsString(new SimpleResponse(message)));

//        }

    }

    protected boolean isConcurrent(){
        return false;
    }

    public String getRedirectURL() {
        return redirectURL;
    }

    public void setRedirectURL(String redirectURL) {
        this.redirectURL = redirectURL;
    }
}
