package com.wang.security.core.validate.code.sender;

/**
 * @Author wangxiamei
 * @Description: 短信验证码发送器
 * @Date 2018/11/29 11:19
 */
public interface CodeSender {
    boolean sendCode(String mobile, String code);
}
