package com.wang.security.browser;

import com.wang.security.core.validate.code.dto.ValidateCode;
import com.wang.security.core.validate.code.process.CodeStore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @Author wangxiamei
 * @Description:
 * @Date 2018/12/17 14:31
 */
 @Component
public class SessionCodeStore implements CodeStore {

    private static final String SESSION_KEY = "SESSION_KEY_CODE";

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Override
    public void resposityCode(ValidateCode code, ServletWebRequest request, String type) {
        sessionStrategy.setAttribute(request, SESSION_KEY + "_" + type.toUpperCase(), code);
    }

    @Override
    public ValidateCode getCode(String type, ServletWebRequest request) {
        return (ValidateCode)sessionStrategy.getAttribute(request, SESSION_KEY + "_" + type.toUpperCase());
    }

    @Override
    public void removeCode(String type, ServletWebRequest request) {
        sessionStrategy.removeAttribute(request, SESSION_KEY + "_" + type.toUpperCase());
    }


}
