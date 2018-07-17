package com.singlesignon.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class OAuth2ServerConfiguration extends AuthorizationServerConfigurerAdapter {

    private final CustomWebSecurityConfigurerAdapter customWebSecurityConfigurerAdapter;

    @Autowired
    public OAuth2ServerConfiguration(CustomWebSecurityConfigurerAdapter customWebSecurityConfigurerAdapter) {
        this.customWebSecurityConfigurerAdapter = customWebSecurityConfigurerAdapter;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("foo")
                .secret("$2a$10$xl9GIaF/n0xrU0SacDAIa.5NvXBsaf4wQybtnmSfk591QCn9uTS/.")
                .authorizedGrantTypes("refresh_token", "password", "authorization_code", "implicit")
                .scopes("read", "write", "trust");
    }


    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(customWebSecurityConfigurerAdapter.authenticationManagerBean())
                .tokenStore(new InMemoryTokenStore());
    }
}
