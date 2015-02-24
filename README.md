#Simple Spring social google

Simple Spring Social Google integration simply allowing to connect with a google account.

The user profile informations are retrieved from the google oauth api v2 (https://www.googleapis.com/oauth2/v2/userinfo) meaning that a google plus account is not needed to connect.

## How to use

### With a GoogleConnectionFactory :
Simply add a `ConnectionFactory` in a `SocialConfigurerAdapter`
```java
@Configuration
@EnableSocial
public class SocialSecurityConfig extends SocialConfigurerAdapter  {
    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer cfConfig, Environment env) {
        cfConfig.addConnectionFactory(new GoogleConnectionFactory(
                googleClientId,
                googleSecret));
    }

    ...
}
```
You'll have to provide the following scope to the oauth request :
`https://www.googleapis.com/auth/userinfo.email https://www.googleapis.com/auth/userinfo.profile`


### With the provided GoogleAuthenticationService :
You can simply add the `GoogleAuthenticationService` to an autowired `SocialAuthenticationServiceRegistry`.
This service add the default needed scopes to the oauth request and can also include the `access_type=offline` parameter.

```java
@Configuration
@EnableSocial
public class SocialSecurityConfig {

    @Autowired
    public void addGoogleConnectionService(SocialAuthenticationServiceRegistry registry) {
        registry.addAuthenticationService(new GoogleAuthenticationService(googleClientId, googleSecret));
        // or if you want offline access
        //registry.addAuthenticationService(new GoogleAuthenticationService(googleClientId, googleSecret).accessOffline(true));
    }

    ...
}
```
