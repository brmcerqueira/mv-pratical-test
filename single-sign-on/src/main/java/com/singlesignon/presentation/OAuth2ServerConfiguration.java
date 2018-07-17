package com.singlesignon.presentation;

import com.singlesignon.business.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableAuthorizationServer
public class OAuth2ServerConfiguration extends AuthorizationServerConfigurerAdapter {

    private final CustomUserDetailsService customUserDetailsService;
    private final TokenStore tokenStore;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public OAuth2ServerConfiguration(CustomUserDetailsService customUserDetailsService,
                                     TokenStore tokenStore,
                                     AuthenticationManager authenticationManager) {
        this.customUserDetailsService = customUserDetailsService;
        this.tokenStore = tokenStore;
        this.authenticationManager = authenticationManager;
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
        endpoints.tokenStore(tokenStore).authenticationManager(authenticationManager)
                .userDetailsService(customUserDetailsService);
    }
}
