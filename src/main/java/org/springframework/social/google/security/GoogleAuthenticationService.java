/**
 * (C) Copyright 2015 Hightea.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.social.google.security;

import org.springframework.social.google.api.Google;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.social.security.SocialAuthenticationRedirectException;
import org.springframework.social.security.SocialAuthenticationToken;
import org.springframework.social.security.provider.OAuth2AuthenticationService;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GoogleAuthenticationService extends OAuth2AuthenticationService<Google> {

    private static final String DEFAULT_SCOPE = "https://www.googleapis.com/auth/userinfo.email https://www.googleapis.com/auth/userinfo.profile";

    private boolean accessOffline;

	public GoogleAuthenticationService(String apiKey, String appSecret) {
		super(new GoogleConnectionFactory(apiKey, appSecret));
        setDefaultScope(DEFAULT_SCOPE);
	}


    public GoogleAuthenticationService accessOffline(boolean accessOffline) {
        this.accessOffline = accessOffline;
        return this;
    }

    //The following allows to use offline access by default

    @Override
    public SocialAuthenticationToken getAuthToken(HttpServletRequest request, HttpServletResponse response) throws SocialAuthenticationRedirectException {
        if (! accessOffline) {
            return super.getAuthToken(request, response);
        }
        String code = request.getParameter("code");
        if (!StringUtils.hasText(code)) {
            OAuth2Parameters params = new OAuth2Parameters();
            params.setRedirectUri(buildReturnToUrl(request));
            setScope(request, params);
            params.add("access_type", "offline");
            params.add("state", getConnectionFactory().generateState());
            throw new SocialAuthenticationRedirectException(getConnectionFactory().getOAuthOperations().buildAuthenticateUrl(params));
        } else {
            return super.getAuthToken(request, response);
        }
    }

    private void setScope(HttpServletRequest request, OAuth2Parameters params) {
        String requestedScope = request.getParameter("scope");
        if (StringUtils.hasLength(requestedScope)) {
            params.setScope(requestedScope);
        } else {
            params.setScope(DEFAULT_SCOPE);
        }
    }
}
