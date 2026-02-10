package com.huuphuoc.api.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

import java.time.Duration;

@Configuration
public class RedisConfig {
    private  final  static  String HOST_NAME = "redis-13344.c56.east-us.azure.cloud.redislabs.com";
     private  final  static String  PASS_WORD = "1oWRt98kmkeo2cO1aI5NOAnClYKrZwaY";
     private  final  static  int POST = 13344;



    @Bean
    public LettuceConnectionFactory lettuceConnectionFactory() {

        LettuceClientConfiguration clientConfig = LettuceClientConfiguration.builder()
//                .useSsl().and()
                .commandTimeout(Duration.ofSeconds(10))
                .shutdownTimeout(Duration.ZERO)
                .build();

        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(HOST_NAME);
        redisStandaloneConfiguration.setPort(POST);
        redisStandaloneConfiguration.setPassword(PASS_WORD);

        return new LettuceConnectionFactory(redisStandaloneConfiguration, clientConfig);
    }
}
