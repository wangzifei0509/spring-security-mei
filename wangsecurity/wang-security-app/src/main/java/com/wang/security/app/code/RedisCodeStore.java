package com.wang.security.app.code;

import com.wang.security.core.validate.code.ValidateCodeException;
import com.wang.security.core.validate.code.dto.ValidateCode;
import com.wang.security.core.validate.code.process.CodeStore;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @Author wangxiamei
 * @Description: 将验证码存在redis中
 * @Date 2018/12/17 14:42
 */
@Component
public class RedisCodeStore implements CodeStore {
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Override
    public void resposityCode(ValidateCode code, ServletWebRequest request, String type) {
        String key = getKey(request, type);
        redisTemplate.opsForValue().set(key, code);
    }

    @Override
    public ValidateCode getCode(String type, ServletWebRequest request) {
        String key = getKey(request, type);
        return (ValidateCode) redisTemplate.opsForValue().get(key);
    }

    @Override
    public void removeCode(String type, ServletWebRequest request) {
        String key = getKey(request, type);
        redisTemplate.delete(key);
    }

    private String getKey(ServletWebRequest request, String type) {
        String diviceId = request.getHeader("diviceid");
        if (StringUtils.isBlank(diviceId)) {
            throw new ValidateCodeException("diviceid must have a value");
        }
        return "code:" + type.toUpperCase() + ":" + diviceId;
    }
}
