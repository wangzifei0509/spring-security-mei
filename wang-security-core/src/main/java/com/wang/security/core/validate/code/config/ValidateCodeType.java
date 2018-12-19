package com.wang.security.core.validate.code.config;

import com.wang.security.core.validate.code.config.SecurityConstants;

/**
 * @Author wangxiamei
 * @Description: 验证码的校验类型
 * @Date 2018/11/30 10:20
 */
public enum ValidateCodeType {
    IMAGE {
        @Override
        public String getParamNameOnValiate() {
            return SecurityConstants.DEFAULT_PARAM_NAME_CODE_IMAGE;
        }
    },
    SMS {
        @Override
        public String getParamNameOnValiate() {
            return SecurityConstants.DEFAULT_PARAM_NAME_CODE_SMS;
        }
    };


    public abstract String getParamNameOnValiate();
}
