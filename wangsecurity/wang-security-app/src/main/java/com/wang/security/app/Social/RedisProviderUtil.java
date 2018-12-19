package com.wang.security.app.Social;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.stereotype.Component;

/**
 * @Author wangxiamei
 * @Description: 由于app环境没有session，将connet放到redis中保存
 * @Date 2018/12/18 10:50
 */
@Component
public class RedisProviderUtil {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private UsersConnectionRepository usersConnectionRepository;

    /**
     * @Author wangxiamei
     * @Description 从redis中获取connection
     * @Date 2018/12/18 10:52
     */
    public Connection<?> getConnectionFromRedis(String openid) {
        String key = "connect:" + openid;
        return (Connection<?>) redisTemplate.opsForValue().get(key);
    }

    /**
     * @Author wangxiamei
     * @Description 获取到userid后登录
     * @Date 2018/12/18 10:54
     */
    public void doPostSignUp(String userId, String openid) {
        String key = "connect:" + openid;
        Connection<?> connection = getConnectionFromRedis(key);
        usersConnectionRepository.createConnectionRepository(userId).addConnection(connection);
        redisTemplate.delete(openid);
    }

    public void setConnectionFromRedis(Connection<?> connection) {
        String key = "connect:" + connection.getKey().getProviderUserId();
        redisTemplate.opsForValue().set(key, connection);
    }

}
