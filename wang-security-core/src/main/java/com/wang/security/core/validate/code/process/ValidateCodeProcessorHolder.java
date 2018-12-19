package com.wang.security.core.validate.code.process;

import com.wang.security.core.validate.code.ValidateCodeException;
import com.wang.security.core.validate.code.config.ValidateCodeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author wangxiamei
 * @Description: 获取具体要使用哪种processor validate
 * @Date 2018/11/30 10:33
 */
@Component
public class ValidateCodeProcessorHolder {
    @Autowired
    private Map<String,ValidateCodeProcessor> validateCodeProcessorMap = new HashMap<>();

    public ValidateCodeProcessor getProcessorByType(ValidateCodeType type){
       return getProcessorByString(type.toString());
    }
    public ValidateCodeProcessor getProcessorByString(String type){
        ValidateCodeProcessor validateCodeProcessor = validateCodeProcessorMap.get(type.toLowerCase() + "CodeProcessor");
        if(validateCodeProcessor == null){
            throw  new ValidateCodeException("validate code processor is not found");
        }
        return validateCodeProcessor;
    }

}
