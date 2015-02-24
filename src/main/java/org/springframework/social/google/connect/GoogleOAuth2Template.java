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
package org.springframework.social.google.connect;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.util.MultiValueMap;

import java.util.Map;

/**
 * Google-specific extension of OAuth2Template.
 */
public class GoogleOAuth2Template extends OAuth2Template {
	
	public GoogleOAuth2Template(String clientId, String clientSecret) {
		super(clientId, clientSecret,
				"https://accounts.google.com/o/oauth2/auth",
				"https://accounts.google.com/o/oauth2/token");
		setUseParametersForClientAuthentication(true);
	}


	@Override
	protected AccessGrant postForAccessGrant(String accessTokenUrl, MultiValueMap<String, String> parameters) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(parameters, headers);
        Map<String, Object> response = getRestTemplate().postForObject(accessTokenUrl, requestEntity, Map.class);
        Number expires = (Number) response.get("expires_in");
        return createAccessGrant((String) response.get("access_token"), (String) response.get("scope"), (String) response.get("refresh_token"), expires != null ? expires.longValue() : null, null);
	}

}
