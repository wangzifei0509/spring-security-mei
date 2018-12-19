package com.wang.security.core.validate.code.genete;

import com.wang.security.core.validate.code.dto.ValidateCode;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @Author wangxiamei
 * @Description: 校验码的生成逻辑
 * @Date 2018/11/28 20:16
 */
public interface GeneteCode {
    ValidateCode genete(ServletWebRequest request);
}
