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
package org.springframework.social.google.api;

/**
 * Defines operations for reading user data from Google.
 */
public interface UserOperations {

    /**
     * Retrieves the profile for the authenticated user.
     * The profile information are retrieved from the https://www.googleapis.com/oauth2/v2/userinfo API
     * @return the user's profile information.
     * @throws org.springframework.social.MissingAuthorizationException if GoogleTemplate was not created with an access token.
     */
    GoogleProfile getUserProfile();
}
