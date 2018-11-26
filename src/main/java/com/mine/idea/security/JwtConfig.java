package com.mine.idea.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @stefanl
 */
@Component
public class JwtConfig {

    @Value("${security.jwt.signUpUrl:/access-tokens**}")
    private String accessTokens;

    @Value("${security.jwt.signUpUrl:/users}")
    private String signUpUrl;

    @Value("${security.jwt.header:X-access-token}")
    private String header;

    @Value("${security.jwt.prefix:Bearer }")
    private String prefix;

    @Value("${security.jwt.expiration:#{24*60*60}}")
    private int expiration;

    @Value("${security.jwt.secret:SecretKeyToGenJWTs}")
    private String secret;

    public String getHeader() {
        return header;
    }

    public String getPrefix() {
        return prefix;
    }

    public int getExpiration() {
        return expiration;
    }

    public String getSecret() {
        return secret;
    }

    public String getSignUpUrl() {
        return signUpUrl;
    }

    public String getAccessTokens() {
        return accessTokens;
    }
}
