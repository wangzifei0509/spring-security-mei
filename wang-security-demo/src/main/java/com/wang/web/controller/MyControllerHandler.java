package com.wang.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author wangxiamei
 * @Description:
 * @Date 2018/11/11 22:29
 */
//@ControllerAdvice
public class MyControllerHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String,Object> handle(Exception e){
        Map<String,Object> result = new HashMap<>();
        result.put("id",1);
        result.put("message","错误");
        return result;
    }
}
