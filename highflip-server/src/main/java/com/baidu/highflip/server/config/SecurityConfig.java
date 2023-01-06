package com.baidu.highflip.server.config;

import com.baidu.highflip.server.engine.HighFlipEngine;
import com.baidu.highflip.server.exception.AuthenticationException;
import lombok.extern.slf4j.Slf4j;

import org.lognet.springboot.grpc.security.GrpcSecurity;
import org.lognet.springboot.grpc.security.GrpcSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.Objects;


@Slf4j
@ConditionalOnProperty(
    value="grpc.security.auth.enabled",
    havingValue = "true",
    matchIfMissing = false)
@Configuration
public class SecurityConfig extends GrpcSecurityConfigurerAdapter {

    @Autowired
    HighFlipEngine engine;

    @Override
    public void configure(GrpcSecurity builder) throws Exception {
        builder.authorizeRequests()
            .anyMethod().authenticated()
            .and()
            .authenticationProvider(new AuthenticationProvider() {
                @Override
                public Authentication authenticate(Authentication authentication) throws AuthenticationException {
                    UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken)authentication;

                    boolean result = engine.validateUser(
                            Objects.toString(auth.getPrincipal()),
                            Objects.toString(auth.getCredentials()));

                    if (!result) {
                        throw new AuthenticationException(String.format("failed to authenticate user: %s",
                                auth.getPrincipal().toString()));
                    }
                    return auth;
                }

                @Override
                public boolean supports(Class<?> authentication) {
                    return UsernamePasswordAuthenticationToken.class.equals(authentication);
                }
            });
    }

}
