package com.huuphuoc.webBH.security.config;

import com.huuphuoc.webBH.common.passwordencoder.PasswordEndcoder;
import com.huuphuoc.webBH.security.service.SecurityDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

   private final SecurityDetailsServiceImpl securityDetailsService;
   private final PasswordEndcoder passwordEndcoder;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 1. Disable CSRF cho Stateless API
                .csrf(csrf -> csrf.disable())

                // 2. Kích hoạt CORS (nếu frontend và backend khác domain/port)
                .cors(Customizer.withDefaults())

                // 3. Quản lý Session: Stateless
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // 4. Phân quyền
                .authorizeHttpRequests(authorize -> authorize
                        // Cho phép preflight requests (CORS)
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                        .requestMatchers(
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/swagger-resources/**",
                                "/webjars/**"
                        ).permitAll() // Mở full quyền cho Swagger

                        // Public endpoints
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/confirm", "/confirm/**").permitAll()

                        .anyRequest().authenticated() // Còn lại phải đăng nhập
                );


        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        // Nạp UserDetailsService vào đây để Spring biết cách tìm user
        authProvider.setUserDetailsService(securityDetailsService);
        authProvider.setPasswordEncoder(passwordEndcoder.bCryptPasswordEncoder());

        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
//        return config.getAuthenticationManager();
    }

}
