package com.wang.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author wangxiamei
 * @Description:
 * @Date 2018/11/25 23:51
 */
@RestController
public class AuthController {
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private ProviderSignInUtils utils;

    @RequestMapping("/me")
    public Authentication getMe() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @PostMapping("/regist")
    public String regist(@RequestParam String username, @RequestParam String password, HttpServletRequest request) {
        utils.doPostSignUp(username, new ServletWebRequest(request));
        return "sucess";
    }
}
