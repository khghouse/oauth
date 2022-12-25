package com.oauth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@Configuration
@EnableResourceServer
// REST API 인증을 처리해주는 역할
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private static final String RESOURCE_ID = "resource_id";

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(RESOURCE_ID).stateless(false);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .anonymous().disable()
                .authorizeRequests()
                    .antMatchers("/users/**").authenticated() // 해당 경로로 접근하는 요청은 인증이 되어야 한다라는 의미
                    .and()
                .exceptionHandling() // 인증이 안되서 에러가 발생한다면
                    .accessDeniedHandler(new OAuth2AccessDeniedHandler()); // 해당 핸들러를 사용한다. OAuth2 에러 관련 응답 메세지 발송
    }
}
