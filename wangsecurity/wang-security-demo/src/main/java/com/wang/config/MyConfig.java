package com.wang.config;

import com.wang.web.filter.MyFilter;
import com.wang.web.intercepter.MyIntercepter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author wangxiamei
 * @Description: 配置文件
 * @Date 2018/11/24 17:36
 */
//@Configuration
public class MyConfig extends WebMvcConfigurerAdapter {

//    @Override
//    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
//
//        configurer.registerCallableInterceptors();
//        configurer.registerDeferredResultInterceptors();
//        configurer.setTaskExecutor();
//        configurer.setDefaultTimeout();
//    }

    @Bean
    public FilterRegistrationBean myFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        Set<String> urls = new HashSet<>();
        urls.add("/hello");
        bean.setUrlPatterns(urls);
        bean.setFilter(new MyFilter());
        return bean;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       registry.addInterceptor(new MyIntercepter());
    }
}
