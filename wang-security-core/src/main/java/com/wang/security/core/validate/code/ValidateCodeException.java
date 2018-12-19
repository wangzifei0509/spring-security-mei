package com.wang.security.core.validate.code;


import org.springframework.security.core.AuthenticationException;

/**
 * @Author wangxiamei
 * @Description:
 * @Date 2018/11/28 17:39
 */
public class ValidateCodeException extends AuthenticationException {

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
