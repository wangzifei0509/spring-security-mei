package com.wang.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author wangxiamei
 * @Description:
 * @Date 2018/11/11 19:58
 */
@ApiModel(description = "qingqiu")
public class UserInfoQuery {
    @ApiModelProperty("查询用户名")
    private String username;
    private int age;
    private int ageTo;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAgeTo() {
        return ageTo;
    }

    public void setAgeTo(int ageTo) {
        this.ageTo = ageTo;
    }

    @Override
    public String toString() {
        return "UserInfoQuery{" +
                "username='" + username + '\'' +
                ", age=" + age +
                ", ageTo=" + ageTo +
                '}';
    }
}
