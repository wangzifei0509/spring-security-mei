package com.wang.web.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Author wangxiamei
 * @Description:
 * @Date 2018/11/24 19:42
 */
@Component
public class OrderQuene {

   private static final Logger logger = LoggerFactory.getLogger(OrderQuene.class);

    private String placeOrderId;
    private String completeOrderId;

    public String getPlaceOrderId() {
        return placeOrderId;
    }

    public void setPlaceOrderId(String placeOrderId) {
       new Thread(()->{
           logger.info("application2 operate order ");
           try {
               Thread.sleep(1000);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           this.completeOrderId = placeOrderId;
           logger.info("application2 operated order ");
       }).start();

    }

    public String getCompleteOrderId() {
        return completeOrderId;
    }

    public void setCompleteOrderId(String completeOrderId) {
        this.completeOrderId = completeOrderId;
    }
}
