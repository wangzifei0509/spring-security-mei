package com.wang.security.core.validate.code.sender;

import org.springframework.stereotype.Component;

/**
 * @Author wangxiamei
 * @Description: 默认的短信验证码发送器
 * @Date 2018/11/29 11:19
 */
@Component
public class DefaultSmsCodeSender implements CodeSender {
    @Override
    public boolean sendCode(String mobile, String code) {
        System.out.println("向"+mobile+"发送短信"+code);
        return true;
    }
}
