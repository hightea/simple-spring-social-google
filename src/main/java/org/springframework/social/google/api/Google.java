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

import org.springframework.social.ApiBinding;


public interface Google extends ApiBinding {

	/**
	 * Retrieves a {@link UserOperations}, used for interacting with Google user API.
	 * This requires OAuth2 scope https://www.googleapis.com/auth/userinfo.profile
	 * 
	 * @return {@link UserOperations} for the authenticated user if authenticated
	 */
	UserOperations userOperations();
}
