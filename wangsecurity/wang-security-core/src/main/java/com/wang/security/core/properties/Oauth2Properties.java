package com.wang.security.core.properties;

/**
 * @Author wangxiamei
 * @Description: 第三方服务的信息
 * @Date 2018/12/18 15:58
 */
public class Oauth2Properties {

    private ClientProperties[] clients;

    private String jwtSignKey = "wang";

    public ClientProperties[] getClients() {
        return clients;
    }

    public void setClients(ClientProperties[] clients) {
        this.clients = clients;
    }

    public String getJwtSignKey() {
        return jwtSignKey;
    }

    public void setJwtSignKey(String jwtSignKey) {
        this.jwtSignKey = jwtSignKey;
    }
}
