package com.wang.security.core.validate.code.process;

import com.wang.security.core.validate.code.dto.ValidateCode;
import com.wang.security.core.validate.code.sender.CodeSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @Author wangxiamei
 * @Description: 短信验证码生成器
 * @Date 2018/11/29 13:38
 */
@Component("smsCodeProcessor")
public class SmsCodeProcessor extends AbstractValidateCodeProcessor {
    private Logger logger = LoggerFactory.getLogger(SmsCodeProcessor.class);

    @Autowired
    private CodeSender codeSender;

    @Override
   public void handleCode(ValidateCode code, ServletWebRequest request) {
        try {
            //发送验证码
            String mobile = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), "mobile");
            codeSender.sendCode(mobile, code.getCode());
        } catch (ServletRequestBindingException e) {
            logger.error("sms validatecode send fail");
        }
    }
}
