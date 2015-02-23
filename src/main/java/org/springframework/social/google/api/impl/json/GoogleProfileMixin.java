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
package org.springframework.social.google.api.impl.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public abstract class GoogleProfileMixin {

    @JsonCreator
    GoogleProfileMixin(
            @JsonProperty("id") String id,
            @JsonProperty("email") String email,
            @JsonProperty("name") String name,
            @JsonProperty("family_name") String familyName,
            @JsonProperty("given_name") String givenName,
            @JsonProperty("picture") String picture,
            @JsonProperty("locale") String locale,
            @JsonProperty("verified_email") boolean verifiedEmail) {}


    @JsonProperty("link")
    private String link;
}
