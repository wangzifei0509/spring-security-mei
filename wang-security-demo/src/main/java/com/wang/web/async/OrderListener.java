package com.wang.web.async;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @Author wangxiamei
 * @Description:
 * @Date 2018/11/24 19:43
 */
@Component
public class OrderListener implements ApplicationListener<ContextRefreshedEvent> {

    private Logger logger  = LoggerFactory.getLogger(OrderListener.class);

    @Autowired
    private OrderQuene quene;
    @Autowired
    private DeferredResultHolder holder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        new Thread(()->{
            while(true){

                if(StringUtils.isNotBlank(quene.getCompleteOrderId())){
                    holder.getMap().get(quene.getCompleteOrderId()).setResult("operate successed");
                    quene.setCompleteOrderId(null);
                    logger.info("return order .....");
                } else {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();
    }
}
