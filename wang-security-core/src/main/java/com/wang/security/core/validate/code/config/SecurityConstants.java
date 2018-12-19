package com.wang.security.core.validate.code.config;

/**
 * @Author wangxiamei
 * @Description: 常量
 * @Date 2018/11/30 10:24
 */
public class SecurityConstants {

    public static final String DEFAULT_PARAM_NAME_CODE_SMS = "smsCode";
    public static final String DEFAULT_PARAM_NAME_CODE_IMAGE = "imageCode";
    public static final String DEFAULT_SMS_URL = "/authen/sms";
    public static final String DEFAULT_IMAGE_URL = "/authen/image";
    public static final String DEFAULT_OPENID_URL = "/authen/openid";

    public static final String DEFAULT_NO_AUTHEN_URL= "/security/require";
    public static final String DEFAULT_NO_AUTHEN_BROWSER_URL= "/wang-signin.html";
    public static final String DEFAULT_NO_SESSION_URL = "/wang-invalid.html";
    public static final String DEFAULT_REGIST_URL = "/regist";

    public static final String VALIDATE_CODE_PREFIX = "/code";

}
