package com.singlesignon.presentation;

import com.singlesignon.business.CustomAuthenticationService;
import com.singlesignon.business.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class CustomWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

    private final CustomUserDetailsService customUserDetailsService;
    private final CustomAuthenticationService customAuthenticationService;

    @Autowired
    public CustomWebSecurityConfigurerAdapter(CustomUserDetailsService customUserDetailsService,
                                              CustomAuthenticationService customAuthenticationService) {
        this.customUserDetailsService = customUserDetailsService;
        this.customAuthenticationService = customAuthenticationService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(customAuthenticationService)
        .userDetailsService(customUserDetailsService)
        .passwordEncoder(new BCryptPasswordEncoder());
    }

}
