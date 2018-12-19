package com.wang.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.sound.midi.SoundbankResource;

/**
 * @Author wangxiamei
 * @Description:
 * @Date 2018/11/24 18:13
 */
//@Aspect
//@Component
public class MyAspect {

    @Pointcut("execution(* com.wang.web.controller.TestController.*(..))")
    public void pointcut(){

    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint){
        System.out.println("myAspect around before .......");
        try {
            Object proceed = joinPoint.proceed();
            return proceed;
        } catch (Throwable throwable) {
            throwable.printStackTrace();

        }
        System.out.println("myAspect around after .......");
        return null;
    }
}
