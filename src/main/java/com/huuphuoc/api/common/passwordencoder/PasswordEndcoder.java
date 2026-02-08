package com.huuphuoc.api.common.passwordencoder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
public class PasswordEndcoder {

    @Bean
     public  BCryptPasswordEncoder bCryptPasswordEncoder () {
         BCryptPasswordEncoder BCEndCoder = new BCryptPasswordEncoder();
         return  BCEndCoder;
     }
}