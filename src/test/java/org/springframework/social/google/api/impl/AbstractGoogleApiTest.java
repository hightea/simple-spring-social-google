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

import org.junit.Before;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.web.client.MockRestServiceServer;

public class AbstractGoogleApiTest {

    protected static final String ACCESS_TOKEN = "someAccessToken";

    protected GoogleTemplate google;
    protected GoogleTemplate unauthorizedGoogle;

	protected MockRestServiceServer mockServer;
	protected MockRestServiceServer unauthorizedMockServer;
	
	@Before
	public void setup() {
		google = new GoogleTemplate(ACCESS_TOKEN);
		mockServer = MockRestServiceServer.createServer(google.getRestTemplate());
        unauthorizedGoogle = new GoogleTemplate();
        unauthorizedMockServer = MockRestServiceServer.createServer(unauthorizedGoogle.getRestTemplate());
	}
	
	protected Resource jsonResource(String filename) {
		return new ClassPathResource(filename + ".json", getClass());
	}
}
