package com.wang.web.filter;

import javax.servlet.*;
import java.io.IOException;
import java.util.Date;

/**
 * @Author wangxiamei
 * @Description: 自定义拦截器 实现计算请求处理时间
 * @Date 2018/11/24 17:25
 */
//@Component
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("customized filter init......");

    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        System.out.println("go into customized filter......");
        long start = new Date().getTime();
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("request take "+(new Date().getTime()-start));
    }

    @Override
    public void destroy() {
        System.out.println("customized filter destory........");
    }
}
