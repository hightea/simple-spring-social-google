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

import org.springframework.social.google.api.GoogleProfile;
import org.springframework.social.google.api.UserOperations;
import org.springframework.web.client.RestTemplate;

public class UserTemplate extends AbstractGoogleOperations implements UserOperations {

    public UserTemplate(RestTemplate restTemplate, boolean isAuthorized) {
        super(restTemplate, isAuthorized);
    }

    @Override
    public GoogleProfile getUserProfile() {
        requireAuthorization();
        return getRestTemplate().getForObject(buildUri("oauth2/v2/userinfo"), GoogleProfile.class);
    }
}
