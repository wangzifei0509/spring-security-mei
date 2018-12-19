package com.wang.web.intercepter;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @Author wangxiamei
 * @Description:
 * @Date 2018/11/24 17:46
 */
@Component
public class MyIntercepter implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        System.out.println("myIntercepter preHandle.....");
        long start = new Date().getTime();
        httpServletRequest.setAttribute("start", start);

        HandlerMethod method = (HandlerMethod) o;
        method.getMethod().getName();
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("myIntercepter postHandle.....");
        Long start = (Long) httpServletRequest.getAttribute("start");
        System.out.println("myIntecepter take " + (new Date().getTime() - start));

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("myIntercepter afterCompletion.....");
        Long start = (Long) httpServletRequest.getAttribute("start");
        System.out.println("myIntecepter take " + (new Date().getTime() - start));
        System.out.println("exception: " + e);

    }
}
