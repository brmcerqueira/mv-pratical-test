package com.resourceaccount.presentation;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableOAuth2Sso
@ComponentScan({
    "com.resourceaccount.presentation",
    "com.resourceaccount.business"
})
@MapperScan("com.resourceaccount.persistence")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}