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

public class GoogleProfile {

    private final String id;
    private final String email;
    private final String name;
    private final String familyName;
    private final String givenName;
    private final String picture;
    private final String locale;
    private final boolean verifiedEmail;
    private String link;

    public GoogleProfile(String id, String email, String name, String familyName, String givenName, String picture, String locale, boolean verifiedEmail) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.familyName = familyName;
        this.givenName = givenName;
        this.picture = picture;
        this.locale = locale;
        this.verifiedEmail = verifiedEmail;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getFamilyName() {
        return familyName;
    }

    public String getGivenName() {
        return givenName;
    }

    public String getPicture() {
        return picture;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLocale() {
        return locale;
    }

    public boolean isVerifiedEmail() {
        return verifiedEmail;
    }
}