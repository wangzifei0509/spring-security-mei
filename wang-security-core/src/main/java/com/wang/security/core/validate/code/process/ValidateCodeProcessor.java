package com.wang.security.core.validate.code.process;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @Author wangxiamei
 * @Description: 校验码生成器
 * @Date 2018/11/29 11:43
 */
public interface ValidateCodeProcessor {

    void create(ServletWebRequest request);

    void validate(ServletWebRequest request);
}
