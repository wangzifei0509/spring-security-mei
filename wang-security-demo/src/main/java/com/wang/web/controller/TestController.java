package com.wang.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author wangxiamei
 * @Description:
 * @Date 2018/11/11 18:37
 */
@RestController
public class TestController {

    @GetMapping("/hello")
    public String hello(){
        System.out.println("success started");
        return "hello!";
    }
}
