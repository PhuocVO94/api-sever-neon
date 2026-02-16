package com.huuphuoc.api.security.config;

import com.huuphuoc.api.common.passwordencoder.PasswordEndcoder;
import com.huuphuoc.api.security.JWTAuthEntryPoint;
import com.huuphuoc.api.security.JWTAuthFilter;
import com.huuphuoc.api.security.JWTGenerator;
import com.huuphuoc.api.security.service.CustomerDetailsServiceImpl;
import com.huuphuoc.api.security.utils.JWTencodeConfig;
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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final PasswordEndcoder passwordEndcoder;
    private  final JWTAuthEntryPoint jwtAuthEntryPoint;
    private  final JWTGenerator jwtGenerator;
    private  final  CustomerDetailsServiceImpl customerDetailsServiceImpl;
    private  final JWTencodeConfig jwTencodeConfig;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 1. Disable CSRF cho Stateless API
                .csrf(csrf -> csrf.disable())
                .exceptionHandling(ex -> ex.authenticationEntryPoint(jwtAuthEntryPoint))

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
                        .requestMatchers("/api/auth/login").permitAll()
                        .requestMatchers("/api/auth/confirm", "/api/auth/confirm/**","/api/auth/resfesh").permitAll()
                        .requestMatchers("/api/users/**").authenticated()
                        .anyRequest().authenticated() // Còn lại phải đăng nhập

                );
        http.addFilterBefore(jwtAuthFilter(),UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        // Nạp UserDetailsService vào đây để Spring biết cách tìm user
        authProvider.setUserDetailsService(customerDetailsServiceImpl);
        authProvider.setPasswordEncoder(passwordEndcoder.bCryptPasswordEncoder());

        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public JWTAuthFilter jwtAuthFilter() {
        return new JWTAuthFilter(jwtGenerator, customerDetailsServiceImpl);
    }

}