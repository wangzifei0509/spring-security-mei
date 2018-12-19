package com.wang.security.app.Social;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * @Author wangxiamei
 * @Description: 在spring初始化所有的bean是，可在初始化之前和初始化之后做一些操作
 * @Date 2018/12/18 15:06
 */
public class SocialConfigBeanProcess implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        return o;
    }

    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {

        if(StringUtils.equals(s,"wangSpringSocialConfigurer")){
            SpringSocialConfigurer configurer = (SpringSocialConfigurer)o;
            configurer.signupUrl("/wangSignUp");
            return configurer;
        }
        return o;
    }
}
