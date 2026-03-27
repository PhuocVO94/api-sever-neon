package com.huuphuoc.api.config;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ConfigCouldDinary {


    @Bean
    public Cloudinary cloudinary() {
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", "cloud-api-image");
        config.put("api_key", "846117431233626");
        config.put("api_secret","-4UFFqdjk35oW75gA0IJJcdgxJk");
        return new Cloudinary(config);
    }
}
