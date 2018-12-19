package com.wang.security.core.validate.code;

import com.wang.security.core.validate.code.process.ValidateCodeProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author wangxiamei
 * @Description:
 * @Date 2018/11/28 16:03
 */
@Controller
public class GeneteCodeController {

    @Autowired
    private Map<String,ValidateCodeProcessor> codeProcessorMap = new HashMap<>();

    @GetMapping("/code/{type}")
    public void createCode(HttpServletRequest request, HttpServletResponse response, @PathVariable String type){
        codeProcessorMap.get(type+"CodeProcessor").create(new ServletWebRequest(request,response));
    }

}
