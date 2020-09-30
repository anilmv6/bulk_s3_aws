package com.gympro.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.web.bind.annotation.RequestMapping;

@EnableAuthorizationServer
@SpringBootApplication
public class AuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }

    @RequestMapping(value = "/**/{path:[^.]*}")
    public String redirect() {
        // Forward to home page so that route is preserved.
        return "forward:/";
    }
}
