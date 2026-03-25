package com.huuphuoc.api.security;

import com.huuphuoc.api.redis.repository.RedisRepository;
import com.huuphuoc.api.security.service.CustomerDetailsServiceImpl;
import com.huuphuoc.api.security.utils.JWTinfor;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.text.ParseException;

@Component
@RequiredArgsConstructor
public class JWTAuthFilter extends OncePerRequestFilter {

    private  final JWTGenerator jwtGenerator;
    private  final CustomerDetailsServiceImpl customerDetailsServiceimpl;
    private final RedisRepository redisRepository;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestHeader = request.getHeader("Authorization");
        String username = null;
        String token = null;
        if (requestHeader != null && requestHeader.startsWith("Bearer")) {
            token = requestHeader.substring(7);

            try {
                username = jwtGenerator.getUsernameFromJWT(token);
                JWTinfor infor = jwtGenerator.pareToken(token);
                String idJ = infor.getJwtID();

                Boolean isBackList = redisRepository.existsById(idJ);

                if (isBackList){

                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.getWriter().write("Token has been logged out!");

                    return;
                }

            }catch (Exception e){
                logger.error("Error extracting username from token", e);
            }

        }
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = customerDetailsServiceimpl.loadUserByUsername(username);
            if (jwtGenerator.validateToken(token)){
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null ,userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);

            }

        }

        filterChain.doFilter(request,response);


    }
}