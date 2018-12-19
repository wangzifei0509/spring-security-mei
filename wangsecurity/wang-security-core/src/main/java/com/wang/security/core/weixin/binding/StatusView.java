package com.wang.security.core.weixin.binding;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author wangxiamei
 * @Description: 处理绑定状态的展示
 * @Date 2018/12/7 17:14
 */
@Component("connect/status")
public class StatusView extends AbstractView {

    private ObjectMapper mapper = new ObjectMapper();
    @Override
    protected void renderMergedOutputModel(Map<String, Object> map, HttpServletRequest httpServletRequest, HttpServletResponse response) throws Exception {

        Map<String,List> connectionMap =(Map<String,List>)map.get("connectionMap");
        Map<String,Boolean> result = new HashMap();
        for (String s : connectionMap.keySet()) {
            result.put(s,CollectionUtils.isNotEmpty(connectionMap.get(s)));
        }
        response.getWriter().write(mapper.writeValueAsString(result));

    }
}
