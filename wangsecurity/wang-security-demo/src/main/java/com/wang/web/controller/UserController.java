package com.wang.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.wang.dto.UserInfo;
import com.wang.dto.UserInfoQuery;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author wangxiamei
 * @Description:
 * @Date 2018/11/11 19:50
 */
@RestController
@RequestMapping("/user")
    public class UserController {

//    @GetMapping
//    @JsonView(UserInfo.UserInfoSimpleView.class)
//    @ApiOperation("查询用户列表")
//    public List<UserInfo> list(UserInfoQuery query) {
//        System.out.println(query);
//        List<UserInfo> userInfos = new ArrayList<>();
//        UserInfo userInfo = new UserInfo();
//        userInfo.setUsername("Tom");
//        userInfo.setPassword("124");
//        userInfo.setId(123);
//        userInfos.add(userInfo);
//        userInfos.add(userInfo);
//        userInfos.add(userInfo);
//        return userInfos;
//    }

    @PostMapping
    @JsonView(UserInfo.UserInfoSimpleView.class)
    @ApiOperation("查询用户列表")
    public UserInfo list(@RequestBody  UserInfoQuery query) {
        System.out.println(query);
        List<UserInfo> userInfos = new ArrayList<>();
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("Tom");
        userInfo.setPassword("124");
        userInfo.setId(123);
        userInfos.add(userInfo);
        userInfos.add(userInfo);
        userInfos.add(userInfo);
        return userInfo;
    }

    @GetMapping("/{id:\\d+}")
    @JsonView(UserInfo.UserInfoDetailView.class)
    public UserInfo findOne(@ApiParam("用户id") @PathVariable String id) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("Tom");
        userInfo.setPassword("124");
        userInfo.setId(123);
        return userInfo;
    }

//    @PostMapping
//    @JsonView(UserInfo.UserInfoDetailView.class)
//    public UserInfo createOne(@Valid @RequestBody UserInfo userInfo) {
//        System.out.println(userInfo.getUsername());
//        System.out.println(userInfo.getPassword());
//        System.out.println(userInfo.getBirthday());
//        userInfo.setId(1);
//        return userInfo;
//    }

    @PutMapping("/{id:\\d+}")
    @JsonView(UserInfo.UserInfoDetailView.class)
    public UserInfo updateOne(@Valid @RequestBody UserInfo userInfo, BindingResult errors) {
        if (errors.hasErrors()) {
            errors.getAllErrors().stream().forEach(error -> {
                FieldError error1 = (FieldError) error;
                System.out.println(error1.getDefaultMessage());
                System.out.println(error1.getField());
            });
        }
//        if(errors.hasErrors()){
//            errors.getAllErrors().get(0).getDefaultMessage();
//        }

        System.out.println(userInfo.getUsername());
        System.out.println(userInfo.getPassword());
        System.out.println(userInfo.getBirthday());
        System.out.println(userInfo.getId());
        userInfo.setId(1);
        return userInfo;
    }

    @DeleteMapping("/{id:\\d+}")
    public void deleteUser(@PathVariable int id) {
        System.out.println(id);
    }
}
