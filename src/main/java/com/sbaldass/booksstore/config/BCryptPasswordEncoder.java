package com.sbaldass.booksstore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BCryptPasswordEncoder {
    @Bean
    public org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder passwordEncoder(){
        return new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
    }
}
