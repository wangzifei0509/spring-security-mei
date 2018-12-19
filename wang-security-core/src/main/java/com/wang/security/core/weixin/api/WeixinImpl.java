/**
 * 
 */
package com.wang.security.core.weixin.api;

import java.nio.charset.Charset;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Weixin API调用模板， scope为Request的Spring bean, 根据当前用户的accessToken创建。
 * 
 * @author zhailiang
 *
 */
public class WeixinImpl extends AbstractOAuth2ApiBinding implements Weixin {
	private String accessToken;
	/**
	 * 
	 */
	private ObjectMapper objectMapper = new ObjectMapper();


	/**
	 * 获取用户信息的url
	 */
	private static final String URL_GET_USER_INFO = "https://api.weixin.qq.com/sns/userinfo";
	
	/**
	 * @param accessToken
	 */
	public WeixinImpl(String accessToken) {
		this.accessToken = accessToken;
	}
	
	/**
	 * 默认注册的StringHttpMessageConverter字符集为ISO-8859-1，而微信返回的是UTF-8的，所以覆盖了原来的方法。
	 */
	protected List<HttpMessageConverter<?>> getMessageConverters() {
		List<HttpMessageConverter<?>> messageConverters = super.getMessageConverters();
		messageConverters.remove(0);
		messageConverters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
		return messageConverters;
	}

	/**
	 * 获取微信用户信息。
	 */
	@Override
	public WeixinUserInfo getUserInfo(String openId) {
		StringBuilder url = new StringBuilder(URL_GET_USER_INFO);
		url.append("?access_token=");
		url.append(accessToken);
		url.append("&openid=");
		url.append(openId);
		url.append("&lang=zh_CN");
		String response = getRestTemplate().getForObject(url.toString(), String.class);
		if(StringUtils.contains(response, "errcode")) {
			return null;
		}
		WeixinUserInfo profile = null;
		try {
			profile = objectMapper.readValue(response, WeixinUserInfo.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return profile;
	}

}
