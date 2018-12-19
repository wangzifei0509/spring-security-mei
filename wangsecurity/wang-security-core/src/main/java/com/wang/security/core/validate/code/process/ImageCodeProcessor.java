package com.wang.security.core.validate.code.process;

import com.wang.security.core.validate.code.dto.ImageCode;
import com.wang.security.core.validate.code.dto.ValidateCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * @Author wangxiamei
 * @Description: 图片验证码生成器
 * @Date 2018/11/29 12:02
 */
@Component("imageCodeProcessor")
public class ImageCodeProcessor extends AbstractValidateCodeProcessor{

    private Logger loggger = LoggerFactory.getLogger(getClass());
    @Override
    public void handleCode(ValidateCode code,ServletWebRequest request) {
        ImageCode imageCode = (ImageCode) code;
        try {
            ImageIO.write(imageCode.getImage(), "JPEG", request.getResponse().getOutputStream());
        } catch (IOException e) {
            loggger.error("image code return fail ");
        }
    }
}
