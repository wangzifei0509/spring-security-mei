package com.wang.security.core.weixin.binding;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.midi.SoundbankResource;
import java.util.Map;

/**
 * @Author wangxiamei
 * @Description:
 * @Date 2018/12/7 18:13
 */
@Component("connect/weixinConnected")
public class resultView extends AbstractView {
    @Override
    protected void renderMergedOutputModel(Map<String, Object> map, HttpServletRequest httpServletRequest, HttpServletResponse response) throws Exception {
        response.setContentType("text/html;charset=utf-8");
        System.out.println(map);
        response.getWriter().write("<htm><h3>绑定成功</h3></htm>");
    }
}
