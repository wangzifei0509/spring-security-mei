package com.wang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author wangxiamei
 * @Description:
 * @Date 2018/11/11 18:29
 */
@SpringBootApplication
@EnableSwagger2
public class DemoApp {
    public static void main(String[] args) {
        SpringApplication.run(DemoApp.class,args);
    }
}
