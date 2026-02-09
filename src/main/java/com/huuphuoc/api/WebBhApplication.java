package com.huuphuoc.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@SpringBootApplication
//@EnableJpaRepositories(basePackages = "com.huuphuoc.api.jpa.repository")
//@EnableRedisRepositories(basePackages = "com.huuphuoc.api.redis.repository")
public class WebBhApplication {

	public static void main(String[] args) {
	 ApplicationContext context =  SpringApplication.run(WebBhApplication.class, args);

	}

}
