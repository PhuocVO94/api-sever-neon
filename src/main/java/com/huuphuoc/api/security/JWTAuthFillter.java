package com.huuphuoc.api.security;

import com.huuphuoc.api.common.Util.ApiConfigUrls;
import com.huuphuoc.api.security.service.CustomerDetailsServiceImpl;
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

// ❌ LƯU Ý QUAN TRỌNG: Không dùng @Component ở đây để tránh Filter chạy 2 lần
//@Component
@RequiredArgsConstructor
public class JWTAuthFillter extends OncePerRequestFilter {

    private final JWTGennerator jwtGennerator;
    private final CustomerDetailsServiceImpl customerDetailsServiceimpl;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();
        return path.startsWith("/api/auth")
                || path.startsWith("/swagger-ui")
                || path.startsWith("/v3/api-docs")
                || path.startsWith("/swagger-resources")
                || path.startsWith("/webjars");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // 1. Lấy token từ Header
        String token = getJwtFromRequest(request);

        // 2. Validate Token TRƯỚC khi gọi Database (Tối ưu hiệu năng)
        // Nếu có token VÀ token hợp lệ thì mới làm tiếp
        if (token != null && jwtGennerator.validateToken(token)) {
            try {
                // 3. Lấy username từ token
                String username = jwtGennerator.getUsernameFromJWT(token);

                // 4. Nếu chưa xác thực trong Context thì mới load DB
                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                    // Gọi DB lấy thông tin user
                    UserDetails userDetails = customerDetailsServiceimpl.loadUserByUsername(username);

                    // Tạo đối tượng Authentication
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    // Lưu vào Context
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (Exception e) {
                logger.error("Cannot set user authentication: {}", e);
            }
        }

        // Cho phép request đi tiếp
        filterChain.doFilter(request, response);
    }

    // Tách hàm nhỏ để lấy token cho gọn code
    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}