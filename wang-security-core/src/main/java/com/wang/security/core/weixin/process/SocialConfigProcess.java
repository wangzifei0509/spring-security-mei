package com.wang.security.core.weixin.process;

import org.springframework.social.security.SocialAuthenticationFilter;

/**
 * @Author wangxiamei
 * @Description: 对社交登录配置 不同处理
 * @Date 2018/12/17 15:39
 */
public interface SocialConfigProcess {

    void process(SocialAuthenticationFilter filter);
}
