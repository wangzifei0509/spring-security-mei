package com.wang.security.core.validate.code.process;

import com.wang.security.core.validate.code.ValidateCodeException;
import com.wang.security.core.validate.code.config.ValidateCodeType;
import com.wang.security.core.validate.code.dto.ValidateCode;
import com.wang.security.core.validate.code.genete.GeneteCode;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author wangxiamei
 * @Description: 抽象的校验码生成器，一样的逻辑可以在这里实现，不一样的方法可以在子类里实现。
 * @Date 2018/11/29 11:44
 */
public abstract class AbstractValidateCodeProcessor implements ValidateCodeProcessor {

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
    @Autowired
    private CodeStore codeStore;

    @Autowired
    private Map<String, GeneteCode> geneteCodeMap = new HashMap<>();

    @Override
    public void create(ServletWebRequest request) {
        ValidateCode validateCode = createCode(request);
        resposityCode(validateCode, request);
        handleCode(validateCode, request);
    }

    private String getType(ServletWebRequest request) {
        String requestURI = request.getRequest().getRequestURI();
        if (StringUtils.isNotBlank(requestURI)) {
            String[] split = requestURI.split("/");
            return split[2];
        }
        throw new ValidateCodeException("can't find validate type");
    }


    //生成验证码
    private ValidateCode createCode(ServletWebRequest request) {
        //根据请求地址的后半截确定使用哪种生成器
        String type = getType(request);
        return geneteCodeMap.get(type + "GeneteCode").genete(request);
    }

    //放到session中
    private void resposityCode(ValidateCode code, ServletWebRequest request) {
        ValidateCode codeNew = new ValidateCode(code.getCode(), code.getExpire());
        String type = getType(request);
        codeStore.resposityCode(codeNew,request,type);
    }

    //发送
    public abstract void handleCode(ValidateCode code, ServletWebRequest request);

    @Override
    public void validate(ServletWebRequest request) {

        ValidateCodeType type = getValidateCodeType(request);

        ValidateCode validateCode = codeStore.getCode(type.toString(),request);
        String requestCode = null;
        try {
            requestCode = ServletRequestUtils.getStringParameter(request.getRequest(), type.getParamNameOnValiate());
        } catch (ServletRequestBindingException e) {
            throw new ValidateCodeException("获取验证码出错");
        }

        if (StringUtils.isBlank(requestCode)) {
            throw new ValidateCodeException("验证码不能为空");
        }
        if (validateCode == null || StringUtils.isBlank(validateCode.getCode())) {
            throw new ValidateCodeException("找不到验证码");
        }

        if (validateCode.isExpired()) {
            codeStore.removeCode(type.toString(),request);
            throw new ValidateCodeException("验证码过期");

        }
        if (!validateCode.getCode().equals(requestCode)) {
            throw new ValidateCodeException("验证码错误");
        }
        codeStore.removeCode(type.toString(),request);

    }

    private ValidateCodeType getValidateCodeType(ServletWebRequest request) {
        String type = StringUtils.substringBefore(getClass().getSimpleName(), "CodeProcessor").toUpperCase();
        return ValidateCodeType.valueOf(type);
    }
}