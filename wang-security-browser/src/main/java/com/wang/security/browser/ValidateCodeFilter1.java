package com.wang.security.browser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("validateCodeFilter")
public class ValidateCodeFilter1 extends OncePerRequestFilter {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private AntPathMatcher antPathMatcher = new AntPathMatcher();


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (antPathMatcher.match("/code/*", request.getRequestURI())){
            //do validate
            // validate fail
           if(false){
               return;
           }
        }
        //validate success
        filterChain.doFilter(request, response);
    }

}
