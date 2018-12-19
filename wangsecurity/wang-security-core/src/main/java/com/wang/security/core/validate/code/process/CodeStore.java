package com.wang.security.core.validate.code.process;

import com.wang.security.core.validate.code.dto.ValidateCode;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @Author wangxiamei
 * @Description: 验证码的存储
 * @Date 2018/12/17 14:26
 */
public interface CodeStore {

    //存储验证码
    void resposityCode(ValidateCode code, ServletWebRequest request,String type);

    //获取验证码
    ValidateCode getCode(String type,ServletWebRequest request);
    //删除验证码
    void removeCode(String type, ServletWebRequest request);
}
