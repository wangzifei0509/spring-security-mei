package com.wang.security.core.validate.code.genete;

import com.wang.security.core.properties.SecurityProperties;
import com.wang.security.core.validate.code.dto.ValidateCode;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @Author wangxiamei
 * @Description: 短信验证码生成器
 * @Date 2018/11/28 20:16
 */
@Component("smsGeneteCode")
public class GeneteSmsCode implements GeneteCode {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public ValidateCode genete(ServletWebRequest request) {
        String code = RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getLength());
        return new ValidateCode(code,securityProperties.getCode().getSms().getExpire());

    }


}
