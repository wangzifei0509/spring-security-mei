package com.wang.security.app.openid;

import com.wang.security.core.validate.code.config.SecurityConstants;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author wangxiamei
 * @Description: 根据openid登录
 * @Date 2018/12/17 16:51
 */
public class OpenidAuthenticaitonFilter extends
        AbstractAuthenticationProcessingFilter {
    // ~ Static fields/initializers
    // =====================================================================================

    public static final String SPRING_SECURITY_FORM_OPENID_KEY = "openid";
    public static final String SPRING_SECURITY_FORM_PROVIDERID_KEY = "providerId";

    private String openidParameter = SPRING_SECURITY_FORM_OPENID_KEY;
    private String providerIdParameter = SPRING_SECURITY_FORM_PROVIDERID_KEY;
    private boolean postOnly = true;

    // ~ Constructors
    // ===================================================================================================

    public OpenidAuthenticaitonFilter() {
        super(new AntPathRequestMatcher(SecurityConstants.DEFAULT_OPENID_URL, "POST"));
    }

    // ~ Methods
    // ========================================================================================================

    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        if (postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }
        String openid = obtainOpenid(request);
        String providerId = obtainProviderId(request);
        if (openid == null) {
            openid = "";
        }
        if (providerId == null) {
            providerId = "";
        }
        openid = openid.trim();
        providerId = providerId.trim();
        OpenidAuthenticationToken authRequest = new OpenidAuthenticationToken(
                openid,providerId);

        // Allow subclasses to set the "details" property
        setDetails(request, authRequest);

        return this.getAuthenticationManager().authenticate(authRequest);
    }


    /**
     * Enables subclasses to override the composition of the username, such as by
     * including additional values and a separator.
     *
     * @param request so that request attributes can be retrieved
     * @return the username that will be presented in the <code>Authentication</code>
     * request token to the <code>AuthenticationManager</code>
     */
    protected String obtainOpenid(HttpServletRequest request) {
        return request.getParameter(openidParameter);
    }
    protected String obtainProviderId(HttpServletRequest request) {
        return request.getParameter(providerIdParameter);
    }
    /**
     * Provided so that subclasses may configure what is put into the authentication
     * request's details property.
     *
     * @param request     that an authentication request is being created for
     * @param authRequest the authentication request object that should have its details
     *                    set
     */
    protected void setDetails(HttpServletRequest request,
                              OpenidAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }


    /**
     * Defines whether only HTTP POST requests will be allowed by this filter. If set to
     * true, and an authentication request is received which is not a POST request, an
     * exception will be raised immediately and authentication will not be attempted. The
     * <tt>unsuccessfulAuthentication()</tt> method will be called as if handling a failed
     * authentication.
     * <p>
     * Defaults to <tt>true</tt> but may be overridden by subclasses.
     */
    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }

    public final String getOpenidParameter() {
        return openidParameter;
    }
}
