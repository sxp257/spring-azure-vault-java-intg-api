package com.sherwin.azure.keyvaultapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class CustomWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

    @Value("${ACCESS-TOKEN-PCG-MICROSERVICE-URL}")
    private String credential;

    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        String userName = credential.split(":", 2)[0];
        String userPass = credential.split(":", 2)[1];

        auth.inMemoryAuthentication()
                .withUser(userName).password(passwordEncoder().encode(userPass))
                .authorities("ROLE_USER");
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
