/**
 *
 */
package com.wang.security.core.weixin;

import com.wang.security.core.weixin.process.SocialConfigProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;
import org.springframework.stereotype.Component;

/**
 * @author zhailiang
 *
 *
 */
public class WangSpringSocialConfigurer extends SpringSocialConfigurer {

	private String filterProcessesUrl;

	private SocialConfigProcess socialConfigProcess;

	public WangSpringSocialConfigurer(String filterProcessesUrl) {
		this.filterProcessesUrl = filterProcessesUrl;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected <T> T postProcess(T object) {
		SocialAuthenticationFilter filter = (SocialAuthenticationFilter) super.postProcess(object);
		filter.setFilterProcessesUrl(filterProcessesUrl);
		//filter.setAuthenticationFailureHandler(failureHandler);
		//filter.setAuthenticationSuccessHandler(sucessHandler);
        if(socialConfigProcess !=null){
            socialConfigProcess.process(filter);
        }
		return (T) filter;
	}

    public SocialConfigProcess getSocialConfigProcess() {
        return socialConfigProcess;
    }

    public void setSocialConfigProcess(SocialConfigProcess socialConfigProcess) {
        this.socialConfigProcess = socialConfigProcess;
    }
}
