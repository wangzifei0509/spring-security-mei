package com.wang.validate;

import com.wang.security.core.validate.code.dto.ImageCode;
import com.wang.security.core.validate.code.genete.GeneteCode;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @Author wangxiamei
 * @Description:
 * @Date 2018/11/28 20:27
 */
//@Component("imageGeneteCode")
public class MyImageCodeGenete implements GeneteCode {


    @Override
    public ImageCode genete(ServletWebRequest request) {
        System.out.println("this is a customed ...");
        return null;
    }
}
