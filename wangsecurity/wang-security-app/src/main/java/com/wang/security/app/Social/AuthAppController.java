package com.wang.security.app.Social;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wang.security.app.Social.RedisProviderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author wangxiamei
 * @Description:
 * @Date 2018/11/25 23:51
 */
@RestController
public class AuthAppController {
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private RedisProviderUtil util;

    @PostMapping("/registApp")
    public String registApp(@RequestParam String username, @RequestParam String password, @RequestParam String openid, HttpServletRequest request) {
        util.doPostSignUp(username, openid);
        return "sucess";
    }
}
