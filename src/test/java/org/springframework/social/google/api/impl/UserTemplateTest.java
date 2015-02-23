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

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.social.NotAuthorizedException;
import org.springframework.social.google.api.GoogleProfile;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import static org.springframework.http.HttpMethod.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.*;

public class UserTemplateTest extends AbstractGoogleApiTest {


    @Test
    public void getUserProfileWithoutPlus() {
        mockServer.expect(requestTo("https://www.googleapis.com/oauth2/v2/userinfo"))
                .andExpect(method(GET))
                .andExpect(header("Authorization", "Bearer someAccessToken"))
                .andRespond(withSuccess(jsonResource("profile"), MediaType.APPLICATION_JSON));

        GoogleProfile profile = google.userOperations().getUserProfile();
        assertSimpleProfileData(profile);
        assertNull(profile.getLink());
    }

    @Test
    public void getUserProfileWithPlus() {
        mockServer.expect(requestTo("https://www.googleapis.com/oauth2/v2/userinfo"))
                .andExpect(method(GET))
                .andExpect(header("Authorization", "Bearer someAccessToken"))
                .andRespond(withSuccess(jsonResource("profile-with-plus"), MediaType.APPLICATION_JSON));

        GoogleProfile profile = google.userOperations().getUserProfile();
        assertSimpleProfileData(profile);
        assertEquals("https://plus.google.com/+JeanMineur", profile.getLink());
    }

    @Test(expected = NotAuthorizedException.class)
    public void getUserProfileUnauthorized() {
        unauthorizedGoogle.userOperations().getUserProfile();
    }

    private void assertSimpleProfileData(GoogleProfile profile) {
        assertEquals("100001234567898765432", profile.getId());
        assertEquals("Jean Mineur", profile.getName());
        assertEquals("Mineur", profile.getFamilyName());
        assertEquals("Jean", profile.getGivenName());
        assertEquals("http://www.gravatar.com/avatar/00000000000000000000000000000000", profile.getPicture());
        assertEquals("jean.mineur@gmail.com", profile.getEmail());
        assertEquals("fr", profile.getLocale());
        assertTrue(profile.isVerifiedEmail());
    }
}
