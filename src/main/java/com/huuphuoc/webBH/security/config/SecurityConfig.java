package com.huuphuoc.webBH.security.config;


import com.huuphuoc.webBH.common.enums.Roles;
import io.swagger.v3.oas.models.PathItem;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return  NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 1. Tắt CSRF (Bắt buộc để test POST API bằng Swagger/Postman)
                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth
                        // Mở Swagger
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()

                        // 2. SỬA LỖI TẠI ĐÂY:
                        // Cách A: Viết đúng chính xác chữ Hoa như trong ảnh của bạn
                        .requestMatchers("/api/users/Register").permitAll()

                        // Hoặc Cách B (Khuyên dùng): Cho phép tất cả API bắt đầu bằng /api/users/
                        // .requestMatchers("/api/users/**").permitAll()

                        // 3. Các request còn lại bắt buộc đăng nhập
                        .anyRequest().authenticated()
                )
                .formLogin(withDefaults())
                .httpBasic(withDefaults());

        return http.build();
    }

}
