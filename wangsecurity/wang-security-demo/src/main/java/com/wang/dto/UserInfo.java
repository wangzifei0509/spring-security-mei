package com.wang.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.wang.web.validator.MyConstraint;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;

/**
 * @Author wangxiamei
 * @Description:
 * @Date 2018/11/11 19:52
 */
@ApiModel("xiangying")
public class UserInfo {

    public interface UserInfoSimpleView{};
    public interface UserInfoDetailView extends  UserInfoSimpleView{};

    @ApiModelProperty("id")
    @NotNull(message = "id不能为空")
    private Integer id;

    //@NotBlank(message = "用户名不能为空")
    @MyConstraint(message = "用户名不符合规则")
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;

    @Past(message = "生日不能是之前的时间")
    private Date birthday;


    @JsonView(UserInfoSimpleView.class)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonView(UserInfoDetailView.class)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonView(UserInfoSimpleView.class)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JsonView(UserInfoDetailView.class)
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
