package com.wang.web.controller;

import com.wang.security.core.properties.SecurityProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.*;
import org.springframework.security.oauth2.common.util.JsonParser;
import org.springframework.security.oauth2.common.util.JsonParserFactory;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * @Author wangxiamei
 * @Description:
 * @Date 2018/12/18 20:07
 */
@RestController
public class TokenController {
    @Autowired
    private SecurityProperties securityProperties;

    @GetMapping("/token")
    public String tokenInfo(HttpServletRequest request) throws UnsupportedEncodingException {
        String authentication = request.getHeader("Authorization");
        String token = StringUtils.substringAfter(authentication, "Bearer ");

        Claims claims = Jwts.parser().setSigningKey(securityProperties.getOauth2().getJwtSignKey().getBytes("utf-8"))
                .parseClaimsJws(token).getBody();
        String smart = (String) claims.get("smart");
        return smart;

    }

    @GetMapping("/token1")
    public Map tokenInfo1(HttpServletRequest request) throws UnsupportedEncodingException {

        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object details = authentication.getDetails();
        String token = ((OAuth2AuthenticationDetails)details).getTokenValue();


        String jwtSignKey = securityProperties.getOauth2().getJwtSignKey();

        SignatureVerifier verifier = new MacSigner(jwtSignKey);
        Jwt jwt = JwtHelper.decodeAndVerify(token, verifier);
        String content = jwt.getClaims();
        JsonParser objectMapper = JsonParserFactory.create();
        Map map = objectMapper.parseMap(content);
        if (map.containsKey("exp") && map.get("exp") instanceof Integer) {
            Integer intValue = (Integer) map.get("exp");
            map.put("exp", new Long((long) intValue));
        }

        System.out.println(map);
        return map;

    }
}
