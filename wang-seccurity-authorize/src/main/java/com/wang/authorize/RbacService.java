package com.wang.authorize;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author wangxiamei
 * @Description:
 * @Date 2018/12/19 16:28
 */
public interface RbacService {
    boolean hasPermission(HttpServletRequest request, Authentication authentication);
}
