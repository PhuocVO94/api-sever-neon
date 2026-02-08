package com.huuphuoc.api.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "AuditorProvider")
public class JPAConfiguration {

    @Bean
    public AuditorAware AuditorProvider(){
        return new AuditorProviderIlm();
    }


    static  class  AuditorProviderIlm implements  AuditorAware<String> {
        @Override
        public Optional<String> getCurrentAuditor() {
            return Optional.of("Anonyous");
        }
    }



}
