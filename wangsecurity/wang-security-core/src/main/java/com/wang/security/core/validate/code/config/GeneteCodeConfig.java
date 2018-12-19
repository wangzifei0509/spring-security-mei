package com.wang.security.core.validate.code.config;

import com.wang.security.core.properties.SecurityProperties;
import com.wang.security.core.validate.code.genete.GeneteCode;
import com.wang.security.core.validate.code.genete.GeneteImageCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author wangxiamei
 * @Description:
 * @Date 2018/11/28 20:21
 */
@Configuration
public class GeneteCodeConfig {
    @Autowired
    private SecurityProperties securityProperties;

    @Bean
    @ConditionalOnMissingBean(name = "imageGeneteCode")
    public GeneteCode imageGeneteCode() {
        GeneteImageCode geneteImageCode = new GeneteImageCode();
        geneteImageCode.setSecurityProperties(securityProperties);
        return geneteImageCode;
    }
}
