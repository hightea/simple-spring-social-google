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
package org.springframework.social.google.api.impl;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.UserOperations;
import org.springframework.social.google.api.impl.json.GoogleModule;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;

/**
 * <p>This is the central class for interacting with Google.</p>
 * <p>
 * If some operations do not require OAuth authentication, you may use
 * a {@link GoogleTemplate} that is created through the default constructor
 * and without any OAuth details.
 * Attempts to perform secured operations through such an instance, however,
 * will result in {@link org.springframework.social.MissingAuthorizationException} being thrown.
 * </p>
 */
public class GoogleTemplate extends AbstractOAuth2ApiBinding implements Google {
	
	private UserOperations userOperations;

    /**
     * Create a new instance of GoogleTemplate.
     * This constructor creates a new GoogleTemplate able to perform unauthenticated operations against Google API.
     * A GoogleTemplate created with this constructor will not support operations requiring authentication.
     * Those operations requiring authentication will throw {@link org.springframework.social.MissingAuthorizationException}.
     */
	public GoogleTemplate() {
		initialize();
	}
	
	/**
	 * Creates a new instance of GoogleTemplate.
	 * This constructor creates the GoogleTemplate using a given access token.
	 * @param accessToken an access token granted by Google after OAuth2 authentication
	 */
	public GoogleTemplate(String accessToken) {
		super(accessToken);
		initialize();
	}

	private void initialize() {
        userOperations = new UserTemplate(getRestTemplate(), isAuthorized());
	}

    @Override
    protected MappingJackson2HttpMessageConverter getJsonMessageConverter() {
        MappingJackson2HttpMessageConverter converter = super.getJsonMessageConverter();
        converter.setObjectMapper(new ObjectMapper().registerModule(new GoogleModule()));
        return converter;
    }
	
	@Override
	public UserOperations userOperations() {
		return userOperations;
	}

}