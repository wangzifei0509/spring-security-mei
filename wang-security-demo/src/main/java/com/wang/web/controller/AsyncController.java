package com.wang.web.controller;

import com.wang.web.async.DeferredResultHolder;
import com.wang.web.async.OrderQuene;
import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.Callable;

/**
 * @Author wangxiamei
 * @Description:
 * @Date 2018/11/24 19:22
 */
@RestController
@RequestMapping("/order")
public class AsyncController {

    private static final Logger logger = LoggerFactory.getLogger(AsyncController.class);

    @Autowired
    private OrderQuene quene;

    @Autowired
    private DeferredResultHolder holder;


    //同步处理
    @GetMapping("/1")
    public String order() {
        logger.info("请求处理order");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String orderId = RandomStringUtils.randomNumeric(8);
        logger.info("处理完成order");
        return orderId;
    }

    //callable
    @GetMapping("/2")
    public Callable<String> order2() {
        logger.info("请求处理order");
        Callable<String> result = new Callable<String>() {
            @Override
            public String call() throws Exception {
                logger.info("子线程请求处理order");
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                logger.info("子线程处理order");
                return RandomStringUtils.randomNumeric(8);
            }
        };
        logger.info("处理完成order");
        return result;
    }

    //DeferredResult
    @GetMapping("/3")
    public DeferredResult<String> order3() {
        logger.info("请求处理order");
        String orderId = RandomStringUtils.randomNumeric(8);
        quene.setPlaceOrderId(orderId);

        DeferredResult<String> result = new DeferredResult<>();
        holder.getMap().put(orderId,result);
        logger.info("处理完成order");
        return result;
    }

}
