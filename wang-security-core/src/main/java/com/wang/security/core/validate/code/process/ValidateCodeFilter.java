package com.wang.security.core.validate.code.process;

import com.wang.security.core.properties.SecurityProperties;
import com.wang.security.core.validate.code.config.SecurityConstants;
import com.wang.security.core.validate.code.config.ValidateCodeType;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author wangxiamei
 * @Description:
 * @Date 2018/11/30 10:41
 */
@Component("validateCodeFilter")
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {

    private Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 系统配置信息
     */
    @Autowired
    private SecurityProperties securityProperties;

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    private Map<String, ValidateCodeType> urlMap = new HashMap<>();
    @Autowired
    private ValidateCodeProcessorHolder processorHolder;
    @Autowired
    private AuthenticationFailureHandler failureHandler;

    @Override
    public void afterPropertiesSet() throws ServletException {
        urlMap.put(SecurityConstants.DEFAULT_IMAGE_URL, ValidateCodeType.IMAGE);
        putUrlMap(securityProperties.getCode().getImage().getUrl(), ValidateCodeType.IMAGE);

        urlMap.put(SecurityConstants.DEFAULT_SMS_URL, ValidateCodeType.SMS);
        putUrlMap(securityProperties.getCode().getSms().getUrl(), ValidateCodeType.SMS);

    }

    private void putUrlMap(String url, ValidateCodeType image) {
        if(StringUtils.isBlank(url)){
            return;
        }
        String[] urls = url.split(",");
        for (String s : urls) {
            urlMap.put(s, image);
        }
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        ValidateCodeType type = getProcessorByUrl(request);
        if(type !=null){
            //需要校验
            logger.info("validate code type {}"+type);
            try {
                ValidateCodeProcessor processorByType = processorHolder.getProcessorByType(type);
                processorByType.validate(new ServletWebRequest(request,response));
            } catch (AuthenticationException e) {
                failureHandler.onAuthenticationFailure(request,response,e);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    /**
     * @Author wangxiamei
     * @Description 根据url获取是使用哪个processor校验
     * @Date 2018/11/30 10:49
     */
    private ValidateCodeType getProcessorByUrl(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        for (String url : urlMap.keySet()) {
            if(antPathMatcher.match(url,requestURI)){
                return urlMap.get(url);
            }
        }
        return null;
    }
}
