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

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.GoogleProfile;
import org.springframework.social.google.api.UserOperations;

import static org.junit.Assert.assertEquals;

public class GoogleAdapterTest {

	private GoogleAdapter apiAdapter = new GoogleAdapter();

	private Google google = Mockito.mock(Google.class);

	@Test
	public void fetchProfile() {

		UserOperations userOperations = Mockito.mock(UserOperations.class);
		Mockito.when(google.userOperations()).thenReturn(userOperations);

		GoogleProfile profile = Mockito.mock(GoogleProfile.class);
		Mockito.when(profile.getName()).thenReturn("Jean Mineur");
		Mockito.when(profile.getGivenName()).thenReturn("Jean");
		Mockito.when(profile.getFamilyName()).thenReturn("Mineur");
		Mockito.when(profile.getEmail()).thenReturn("jean.mineur@gmail.com");
		Mockito.when(profile.getId()).thenReturn("100001234567898765432");

		Mockito.when(userOperations.getUserProfile()).thenReturn(profile);
		UserProfile userProfile = apiAdapter.fetchUserProfile(google);
		assertEquals("Jean Mineur", userProfile.getName());
		assertEquals("Jean", userProfile.getFirstName());
		assertEquals("Mineur", userProfile.getLastName());
		assertEquals("jean.mineur@gmail.com", userProfile.getEmail());
		assertEquals("100001234567898765432", userProfile.getUsername());
	}

}
